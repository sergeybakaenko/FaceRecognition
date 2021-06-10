package com.bakaenko.facerecognition.ml

import android.content.Context
import android.graphics.BitmapFactory
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonResponse
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception

class FaceRecognitionServiceImpl(private val context: Context) : FaceRecognitionService {

    private val options = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
        .enableTracking()
        .build()

    private val detector = FaceDetection.getClient(options)

    private suspend fun detectFace(image: InputImage): Face? {
        delay(500)
        return suspendCancellableCoroutine { continuation ->
            detector.process(image).addOnSuccessListener {
                continuation.resumeWith(Result.success(it.maxByOrNull { it.boundingBox.width() * it.boundingBox.height() }))
            }.addOnFailureListener {
                throw it
            }
        }
    }

    override suspend fun orderImagesByFacePresentPercentage(images: List<PersonResponse>): List<PersonModel> {
        val (detectedFaces, undetectedFaces) = images.map {
            if (it.image != null) {
                val bitmap = BitmapFactory.decodeStream(context.assets.open(it.image))
                val inputImage = InputImage.fromBitmap(bitmap, 0)
                val face = try {
                    detectFace(inputImage)
                } catch (exception: Exception) {
                    null
                }
                Triple(face, inputImage, it)
            } else {
                null
            }
        }.partition {
            it?.first != null
        }

        val sortedDetectedFaces = detectedFaces.sortedByDescending {
            if (it?.first == null) return@sortedByDescending 0

            val faceSquare =
                (it.first?.boundingBox?.height() ?: 0) * (it.first?.boundingBox?.width() ?: 0)
            val imageSquare = it.second.height * it.second.width


            ((faceSquare.toDouble() / imageSquare.toDouble()) * 100.0).toInt()
        }

        val sortedUndetectedFaces = undetectedFaces.sortedBy { it?.third?.name }
        val sortedAll = sortedDetectedFaces + sortedUndetectedFaces

        return sortedAll.map {
            PersonModel(it?.first, it?.third?.name ?: "", it?.third?.image)
        }
    }
}
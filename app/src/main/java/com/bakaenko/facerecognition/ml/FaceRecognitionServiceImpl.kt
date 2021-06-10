package com.bakaenko.facerecognition.ml

import android.content.Context
import android.graphics.BitmapFactory
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonResponse
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel
import com.bakaenko.facerecognition.utils.getPercentageFrom
import com.bakaenko.facerecognition.utils.zeroIfNull
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception

class FaceRecognitionServiceImpl(private val context: Context) : FaceRecognitionService {

    private val detector = FaceDetection.getClient()

    private suspend fun detectFace(image: InputImage): Face? {
        return suspendCancellableCoroutine { continuation ->
            detector.process(image).addOnSuccessListener { faces ->
                continuation.resumeWith(Result.success(faces.maxByOrNull { it.boundingBox.width() * it.boundingBox.height() }))
            }.addOnFailureListener {
                throw it
            }
        }
    }

    override suspend fun orderImagesByFacePresentPercentage(images: List<PersonResponse>): List<PersonModel> {
        val (detectedFaces, undetectedFaces) = images.map {
            detectFaceIfPresent(it)
        }.partition {
            it.first != null
        }

        val sortedDetectedFaces = detectedFaces.sortedByDescending {
            if (it.first == null) return@sortedByDescending 0

            val faceBox = it.first?.boundingBox
            val faceArea = faceBox?.height().zeroIfNull() * faceBox?.width().zeroIfNull()
            val imageArea = it.second?.height.zeroIfNull() * it.second?.width.zeroIfNull()

            faceArea.getPercentageFrom(imageArea)
        }

        val sortedUndetectedFaces = undetectedFaces.sortedBy { it.third.name }
        val sortedAll = sortedDetectedFaces + sortedUndetectedFaces

        return sortedAll.map {
            PersonModel(it.first, it.third.name, it.third.image)
        }
    }

    private suspend fun detectFaceIfPresent(person: PersonResponse): Triple<Face?, InputImage?, PersonResponse> {
        return if (person.image != null) {
            val bitmap = BitmapFactory.decodeStream(context.assets.open(person.image))
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val face = try {
                detectFace(inputImage)
            } catch (exception: Exception) {
                null
            }
            Triple(face, inputImage, person)
        } else {
            Triple(null, null, person)
        }
    }
}
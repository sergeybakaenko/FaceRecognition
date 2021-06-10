package com.bakaenko.facerecognition.features.data.model

import com.google.mlkit.vision.face.Face

class PersonsListModel(val items: List<PersonModel>) {
    class PersonModel(val face: Face?, val name: String, val image: String?)
}
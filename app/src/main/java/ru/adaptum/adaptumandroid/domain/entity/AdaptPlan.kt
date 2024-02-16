package ru.adaptum.adaptumandroid.domain.entity

data class AdaptPlan(
    val id: Int,
    val mentor: MentorInfo,
    val groupName: String,
    val adaptPlanName: String,
    val countStages: Int,
    val countMentors: Int,
    val countMaterials: Int,
    val durationDays: Int,
    val startDate: String,
)

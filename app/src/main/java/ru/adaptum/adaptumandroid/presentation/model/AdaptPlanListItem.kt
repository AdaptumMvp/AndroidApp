package ru.adaptum.adaptumandroid.presentation.model

import ru.adaptum.adaptumandroid.domain.entity.AdaptPlan
import ru.adaptum.adaptumandroid.domain.entity.MentorInfo
import ru.adaptum.adaptumandroid.presentation.diffUtils.ListItem

data class AdaptPlanListItem(
    override val id: Int,
    val mentor: MentorInfo,
    val groupName: String,
    val adaptPlanName: String,
    val countStages: String,
    val countMentors: String,
    val countMaterials: String,
    val durationDays: String,
    val startDate: String,
) : ListItem<AdaptPlanListItem> {
    companion object {
        fun fromModel(adaptPlan: AdaptPlan) =
            AdaptPlanListItem(
                id = adaptPlan.id,
                groupName = adaptPlan.groupName,
                adaptPlanName = adaptPlan.adaptPlanName,
                countStages = adaptPlan.countStages.toString(),
                countMentors = adaptPlan.countMentors.toString(),
                countMaterials = adaptPlan.countMaterials.toString(),
                durationDays = adaptPlan.durationDays.toString(),
                mentor = adaptPlan.mentor,
                startDate = adaptPlan.startDate,
            )
    }

    override fun contentEquals(item: ListItem<AdaptPlanListItem>): Boolean {
        return this == item
    }

    override val content: AdaptPlanListItem
        get() = this
}

package com.cupcake.ui.jobs


import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentAboutJobCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel

class AboutJobCategory : BaseFragment<FragmentAboutJobCategoryBinding, JobViewModel>(
    R.layout.fragment_about_job_category,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expandCard(binding!!.textViewDescriptionTitle,binding!!.textViewDescription)
    }

    private fun expandCard(textViewTitle : TextView, textViewDescription: TextView) {
        textViewTitle.setOnClickListener {
            binding?.apply {
                if (textViewDescription.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardViewExpandableDescription,
                        AutoTransition()
                    )
                    textViewDescription.visibility = View.VISIBLE
                } else textViewDescription.visibility = View.GONE
            }
        }
    }
}
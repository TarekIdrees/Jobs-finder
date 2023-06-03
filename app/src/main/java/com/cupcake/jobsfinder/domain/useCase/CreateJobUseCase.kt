package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.repository.Repository
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(paramCreateJob: ParamJobInfo): Boolean {
        return repository.createJob(paramCreateJob.toJobDto())
    }

    data class ParamJobInfo(
        val jobTitleId: Long,
        val company: String,
        val workType: String,
        val jobType: String,
        val jobLocation: String,
        val jobDescription: String,
        val salary: String,
    )

    private fun ParamJobInfo.toJobDto(): JobDto {
        return JobDto(
            jobTitleId = jobTitleId,
            company = company,
            workType = workType,
            jobType = jobType,
            jobLocation = jobLocation,
            jobDescription = jobDescription,
            jobSalary = salary,

            )
    }
}
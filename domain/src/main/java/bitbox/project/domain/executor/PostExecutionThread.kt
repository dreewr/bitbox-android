package bitbox.project.domain.executor

import io.reactivex.Scheduler

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 2/28/19.
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}
package bitbox.project.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import bitbox.project.domain.executor.PostExecutionThread
import javax.inject.Inject
import io.reactivex.Scheduler

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 */

class UiThread @Inject constructor(): PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
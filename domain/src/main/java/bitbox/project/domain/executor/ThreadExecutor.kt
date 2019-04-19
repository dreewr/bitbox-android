package bitbox.project.domain.executor

import io.reactivex.Scheduler
import java.util.concurrent.Executor

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
interface ThreadExecutor : Executor{
    val scheduler: Scheduler
}
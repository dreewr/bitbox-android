package bitbox.project.data.repository

import io.reactivex.Observable
import bitbox.project.domain.model.BlockResponse

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.
 */

interface Remote {

    fun getBlock(blochHash: String): Observable<BlockResponse>

}
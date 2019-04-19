package bitbox.projec.remote

import io.reactivex.Observable
import bitbox.projec.remote.service.RetrofitService
import bitbox.project.data.repository.Remote
import bitbox.project.domain.model.BlockResponse
import javax.inject.Inject

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/11/19.
 *
 * Class that implements the Remote interface from Data module. This class uses retrofit and depends on a RetrofitService
 */

class RemoteImpl @Inject constructor(
        private val service: RetrofitService) : Remote {

    override fun getBlock(blockHash: String): Observable<BlockResponse> {

        return service.getBlock(blockHash)
    }
}
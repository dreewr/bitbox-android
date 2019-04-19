package bitbox.project.data.store

import bitbox.project.domain.model.BlockResponse
import io.reactivex.Observable
import bitbox.project.data.repository.Remote
import javax.inject.Inject


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.

 */
open class RemoteDataStore @Inject constructor(
        private val remote: Remote)
    : DataStore {

    override fun getBlock(blockHash : String): Observable<BlockResponse> {
        return remote.getBlock(blockHash)
    }

}
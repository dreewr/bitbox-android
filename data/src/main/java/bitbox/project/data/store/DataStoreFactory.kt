package bitbox.project.data.store

import javax.inject.Inject

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 *
 * This class provides DataRepository with the correct dataSource, being it Cache information, Remote information or Device information.
 *
 * TODO: The use of a Factory pattern in this case might seem overengineered, but this is a starting point, in the future the logic in this class will show its usefulness
 */

open class DataStoreFactory @Inject constructor(
        //private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val remoteDataStore: RemoteDataStore) {

    open fun getDataStore(): DataStore {
        return remoteDataStore
    }
}
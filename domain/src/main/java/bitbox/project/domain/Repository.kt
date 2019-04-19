package bitbox.project.domain

import io.reactivex.Observable
import io.ubivis.domain.model.BlockResponse

/**
 * Created by André Luiz Rodrigues dos Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/1/19.
 *
 * Classe que centraliza todas as operações de dados dos aplicativos. Cada função
 * definida aqui está ligada a uma classe de use-case
 */

interface Repository{

    fun getBlock(blockHash : String): Observable<BlockResponse>

}

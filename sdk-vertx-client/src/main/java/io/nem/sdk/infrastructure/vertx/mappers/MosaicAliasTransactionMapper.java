/*
 * Copyright 2019. NEM
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.nem.sdk.infrastructure.vertx.mappers;

import io.nem.core.utils.MapperUtils;
import io.nem.sdk.model.blockchain.NetworkType;
import io.nem.sdk.model.namespace.AliasAction;
import io.nem.sdk.model.namespace.NamespaceId;
import io.nem.sdk.model.transaction.JsonHelper;
import io.nem.sdk.model.transaction.MosaicAliasTransaction;
import io.nem.sdk.model.transaction.MosaicAliasTransactionFactory;
import io.nem.sdk.model.transaction.TransactionFactory;
import io.nem.sdk.model.transaction.TransactionType;
import io.nem.sdk.openapi.vertx.model.MosaicAliasTransactionDTO;

class MosaicAliasTransactionMapper extends
    AbstractTransactionMapper<MosaicAliasTransactionDTO, MosaicAliasTransaction> {

    public MosaicAliasTransactionMapper(JsonHelper jsonHelper) {
        super(jsonHelper, TransactionType.MOSAIC_ALIAS, MosaicAliasTransactionDTO.class);
    }

    @Override
    protected TransactionFactory<MosaicAliasTransaction> createFactory(NetworkType networkType,
        MosaicAliasTransactionDTO transaction) {
        NamespaceId namespaceId = MapperUtils.toNamespaceId(transaction.getNamespaceId());
        AliasAction aliasAction = AliasAction
            .rawValueOf(transaction.getAliasAction().getValue().byteValue());
        return new MosaicAliasTransactionFactory(
            networkType,
            aliasAction,
            namespaceId,
            MapperUtils.toMosaicId(transaction.getMosaicId()));
    }
}
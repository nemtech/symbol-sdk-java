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

package io.nem.sdk.infrastructure.okhttp.mappers;

import static io.nem.core.utils.MapperUtils.toMosaicId;

import io.nem.sdk.model.blockchain.NetworkType;
import io.nem.sdk.model.mosaic.MosaicSupplyChangeActionType;
import io.nem.sdk.model.transaction.JsonHelper;
import io.nem.sdk.model.transaction.MosaicSupplyChangeTransaction;
import io.nem.sdk.model.transaction.MosaicSupplyChangeTransactionFactory;
import io.nem.sdk.model.transaction.TransactionFactory;
import io.nem.sdk.model.transaction.TransactionType;
import io.nem.sdk.openapi.okhttp_gson.model.MosaicSupplyChangeTransactionDTO;

/**
 * Mosaic supply change transaction mapper.
 */
class MosaicSupplyChangeTransactionMapper extends
    AbstractTransactionMapper<MosaicSupplyChangeTransactionDTO, MosaicSupplyChangeTransaction> {

    public MosaicSupplyChangeTransactionMapper(JsonHelper jsonHelper) {
        super(jsonHelper, TransactionType.MOSAIC_SUPPLY_CHANGE,
            MosaicSupplyChangeTransactionDTO.class);
    }

    @Override
    protected TransactionFactory<MosaicSupplyChangeTransaction> createFactory(
        NetworkType networkType, MosaicSupplyChangeTransactionDTO transaction) {
        return MosaicSupplyChangeTransactionFactory.create(networkType,
            toMosaicId(transaction.getMosaicId()),
            MosaicSupplyChangeActionType.rawValueOf(transaction.getAction().getValue()),
            transaction.getDelta());
    }
}

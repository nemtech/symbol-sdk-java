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
import io.nem.sdk.model.transaction.JsonHelper;
import io.nem.sdk.model.transaction.MosaicGlobalRestrictionTransaction;
import io.nem.sdk.model.transaction.MosaicGlobalRestrictionTransactionFactory;
import io.nem.sdk.model.transaction.MosaicRestrictionType;
import io.nem.sdk.model.transaction.TransactionFactory;
import io.nem.sdk.model.transaction.TransactionType;
import io.nem.sdk.openapi.okhttp_gson.model.GlobalMosaicRestrictionTransactionDTO;
import java.math.BigInteger;

/**
 * Mosaic global restriction transaction mapper.
 */
class MosaicGlobalRestrictionTransactionMapper extends
    AbstractTransactionMapper<GlobalMosaicRestrictionTransactionDTO, MosaicGlobalRestrictionTransaction> {

    public MosaicGlobalRestrictionTransactionMapper(JsonHelper jsonHelper) {
        super(jsonHelper, TransactionType.MOSAIC_GLOBAL_RESTRICTION, GlobalMosaicRestrictionTransactionDTO.class);
    }

    @Override
    protected TransactionFactory<MosaicGlobalRestrictionTransaction> createFactory(NetworkType networkType,
        GlobalMosaicRestrictionTransactionDTO transaction) {

        byte prevRestrictionType = transaction.getPreviousRestrictionType().getValue().byteValue();
        byte newRestrictionType = transaction.getNewRestrictionType().getValue().byteValue();

        return new MosaicGlobalRestrictionTransactionFactory(networkType,
            toMosaicId(transaction.getMosaicId()),
            toMosaicId(transaction.getReferenceMosaicId()),
            new BigInteger(transaction.getRestrictionKey()),
            new BigInteger(transaction.getPreviousRestrictionValue()),
            MosaicRestrictionType.rawValueOf(prevRestrictionType),
            new BigInteger(transaction.getNewRestrictionValue()),
            MosaicRestrictionType.rawValueOf(newRestrictionType)
        );
    }
}
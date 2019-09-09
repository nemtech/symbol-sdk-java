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

import io.nem.sdk.model.transaction.Transaction;
import io.nem.sdk.model.transaction.TransactionType;
import io.nem.sdk.openapi.okhttp_gson.model.EmbeddedTransactionInfoDTO;
import io.nem.sdk.openapi.okhttp_gson.model.TransactionInfoDTO;


/**
 * A transaction mapper knows how to map DTO for standard and embedded transactions into a {@link
 * Transaction} object.
 */
public interface TransactionMapper {

    /**
     * It maps an embedded transaction included in an aggregate transaction.
     *
     * @param transactionInfoDTO the embedded transaction
     * @return the {@link Transaction}
     */
    Transaction map(EmbeddedTransactionInfoDTO transactionInfoDTO);

    /**
     * It maps a general transaction included in a top level json response.
     *
     * @param transactionInfoDTO the the general transaction
     * @return the {@link Transaction}
     */
    Transaction map(TransactionInfoDTO transactionInfoDTO);

    /**
     * The type of transactions this mapper supports.
     *
     * @return the supported transaction type or null if supports all.
     */
    TransactionType getTransactionType();
}

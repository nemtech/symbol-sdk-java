/*
 *  Copyright 2019 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nem.sdk.infrastructure.vertx;

import io.nem.sdk.api.QueryParams;
import io.nem.sdk.model.blockchain.BlockInfo;
import io.nem.sdk.model.blockchain.MerkelProofInfo;
import io.nem.sdk.model.blockchain.NetworkType;
import io.nem.sdk.model.receipt.Statement;
import io.nem.sdk.model.transaction.Transaction;
import io.nem.sdk.model.transaction.UInt64;
import io.nem.sdk.openapi.vertx.model.BlockDTO;
import io.nem.sdk.openapi.vertx.model.BlockInfoDTO;
import io.nem.sdk.openapi.vertx.model.BlockMetaDTO;
import io.nem.sdk.openapi.vertx.model.EntityTypeEnum;
import io.nem.sdk.openapi.vertx.model.MerklePathItem;
import io.nem.sdk.openapi.vertx.model.MerkleProofInfoDTO;
import io.nem.sdk.openapi.vertx.model.ResolutionStatementDTO;
import io.nem.sdk.openapi.vertx.model.StatementsDTO;
import io.nem.sdk.openapi.vertx.model.TransactionInfoDTO;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for {@link NetworkRepositoryVertxImpl}
 *
 * @author Fernando Boucquez
 */
public class BlockRepositoryVertxImplTest extends AbstractVertxRespositoryTest {

    private BlockRepositoryVertxImpl repository;

    @BeforeEach
    public void setUp() {
        super.setUp();
        repository = new BlockRepositoryVertxImpl(apiClientMock, networkType);
    }

    @Test
    public void shouldGetBlockByHeight() throws Exception {

        BlockInfoDTO dto = new BlockInfoDTO();
        BlockMetaDTO metaDTO = new BlockMetaDTO();
        metaDTO.setHash("someHash");
        metaDTO.setNumTransactions(10);
        metaDTO.setGenerationHash("generationHash");
        metaDTO.setNumStatements(20);
        metaDTO.setSubCacheMerkleRoots(Arrays.asList("string1", "string2"));
        metaDTO.setTotalFee(Arrays.asList(8L, 0L));

        dto.setMeta(metaDTO);

        BlockDTO blockDto = new BlockDTO();
        blockDto.setType(EntityTypeEnum.NUMBER_16716);
        blockDto.setVersion(36867);
        blockDto.setSigner("B630EFDDFADCC4A2077AB8F1EC846B08FEE2D2972EACF95BBAC6BFAC3D31834C");
        blockDto.setBeneficiary("B630EFDDFADCC4A2077AB8F1EC846B08FEE2D2972EACF95BBAC6BFAC3D31834C");
        blockDto.setHeight(Arrays.asList(9L, 0L));

        dto.setBlock(blockDto);

        mockRemoteCall(dto);

        BigInteger height = BigInteger.valueOf(10L);
        BlockInfo info = repository.getBlockByHeight(height).toFuture().get();

        Assertions.assertNotNull(info);

        Assertions.assertEquals(blockDto.getBeneficiary(),
            info.getBeneficiaryPublicAccount().getPublicKey().toString());

        Assertions.assertEquals(blockDto.getSigner(),
            info.getSignerPublicAccount().getPublicKey().toString());

        Assertions.assertEquals(16716, info.getType());
        Assertions.assertEquals(3, info.getVersion().intValue());
        Assertions.assertEquals(NetworkType.MIJIN_TEST, info.getNetworkType());
        Assertions.assertEquals(BigInteger.valueOf(9L), info.getHeight());
        Assertions.assertEquals(metaDTO.getHash(), info.getHash());
        Assertions.assertEquals(metaDTO.getNumTransactions(), info.getNumTransactions());
        Assertions.assertEquals(metaDTO.getGenerationHash(), info.getGenerationHash());
        Assertions.assertEquals(metaDTO.getNumTransactions(), info.getNumTransactions());
        Assertions.assertEquals(metaDTO.getSubCacheMerkleRoots(), info.getSubCacheMerkleRoots());
        Assertions
            .assertEquals(UInt64.extractBigInteger(metaDTO.getTotalFee()), info.getTotalFee());

        Assertions.assertEquals(UInt64.extractBigInteger(blockDto.getHeight()), info.getHeight());

    }

    @Test
    public void shouldGetBlocksByHeightWithLimit() throws Exception {

        BlockInfoDTO dto = new BlockInfoDTO();
        BlockMetaDTO metaDTO = new BlockMetaDTO();
        metaDTO.setHash("someHash");
        metaDTO.setNumTransactions(10);
        metaDTO.setGenerationHash("generationHash");
        metaDTO.setNumStatements(20);
        metaDTO.setSubCacheMerkleRoots(Arrays.asList("string1", "string2"));
        metaDTO.setTotalFee(Arrays.asList(8L, 0L));

        dto.setMeta(metaDTO);

        BlockDTO blockDto = new BlockDTO();
        blockDto.setType(EntityTypeEnum.NUMBER_16716);
        blockDto.setVersion(36867);
        blockDto.setSigner("B630EFDDFADCC4A2077AB8F1EC846B08FEE2D2972EACF95BBAC6BFAC3D31834C");
        blockDto.setBeneficiary("B630EFDDFADCC4A2077AB8F1EC846B08FEE2D2972EACF95BBAC6BFAC3D31834C");
        blockDto.setHeight(Arrays.asList(9L, 0L));

        dto.setBlock(blockDto);

        mockRemoteCall(Collections.singletonList(dto));

        BigInteger height = BigInteger.valueOf(10L);
        BlockInfo info = repository
            .getBlocksByHeightWithLimit(height, 1, Optional.of(new QueryParams(10, "someId")))
            .toFuture().get().get(0);

        Assertions.assertNotNull(info);

        Assertions.assertEquals(blockDto.getBeneficiary(),
            info.getBeneficiaryPublicAccount().getPublicKey().toString());

        Assertions.assertEquals(blockDto.getSigner(),
            info.getSignerPublicAccount().getPublicKey().toString());

        Assertions.assertEquals(16716, info.getType());
        Assertions.assertEquals(3, info.getVersion().intValue());
        Assertions.assertEquals(NetworkType.MIJIN_TEST, info.getNetworkType());
        Assertions.assertEquals(BigInteger.valueOf(9L), info.getHeight());
        Assertions.assertEquals(metaDTO.getHash(), info.getHash());
        Assertions.assertEquals(metaDTO.getNumTransactions(), info.getNumTransactions());
        Assertions.assertEquals(metaDTO.getGenerationHash(), info.getGenerationHash());
        Assertions.assertEquals(metaDTO.getNumTransactions(), info.getNumTransactions());
        Assertions.assertEquals(metaDTO.getSubCacheMerkleRoots(), info.getSubCacheMerkleRoots());
        Assertions
            .assertEquals(UInt64.extractBigInteger(metaDTO.getTotalFee()), info.getTotalFee());

        Assertions.assertEquals(UInt64.extractBigInteger(blockDto.getHeight()), info.getHeight());

    }


    @Test
    public void shouldGetBlockTransactions() throws Exception {
        TransactionInfoDTO transactionInfoDTO = TestHelperVertx.loadTransactionInfoDTO(
            "shouldCreateAggregateMosaicCreationTransaction.json");

        mockRemoteCall(Collections.singletonList(transactionInfoDTO));

        BigInteger height = BigInteger.valueOf(10L);
        List<Transaction> transactions = repository.getBlockTransactions(height).toFuture().get();

        Assertions.assertNotNull(transactions);

        Assertions.assertEquals(1, transactions.size());

    }

    @Test
    public void shouldGetMerkleReceipts() throws Exception {
        MerkleProofInfoDTO merkleProofInfoDTO = new MerkleProofInfoDTO();
        MerklePathItem marklePathItem = new MerklePathItem();
        marklePathItem.setHash("SomeHash");
        marklePathItem.setPosition(123);
        merkleProofInfoDTO.setMerklePath(Collections.singletonList(marklePathItem));

        mockRemoteCall(merkleProofInfoDTO);

        BigInteger height = BigInteger.valueOf(10L);
        MerkelProofInfo info = repository.getMerkleReceipts(height, "AnotherHash").toFuture()
            .get();

        Assertions.assertNotNull(info);

        Assertions.assertEquals(1, info.getPayload().size());
        Assertions.assertEquals(marklePathItem.getHash(), info.getPayload().get(0).getHash());
        Assertions
            .assertEquals(marklePathItem.getPosition(), info.getPayload().get(0).getPosition());
    }

    @Test
    public void shouldGetBlockReceipts() throws Exception {
        StatementsDTO dto = new StatementsDTO();
        ResolutionStatementDTO addressResolutionStatement = new ResolutionStatementDTO();
        addressResolutionStatement
            .setUnresolved("9050B9837EFAB4BBE8A4B9BB32D812F9885C00D8FC1650E142");
        addressResolutionStatement.setHeight(Arrays.asList(6L, 0L));
        dto.setAddressResolutionStatements(Collections.singletonList(addressResolutionStatement));

        ResolutionStatementDTO mosaicResolutionStatement = new ResolutionStatementDTO();
        mosaicResolutionStatement.setHeight(Arrays.asList(7L, 0L));
        mosaicResolutionStatement.setUnresolved(Arrays.asList(9L, 0L));
        dto.setMosaicResolutionStatements(Collections.singletonList(mosaicResolutionStatement));

        mockRemoteCall(dto);

        BigInteger height = BigInteger.valueOf(10L);
        Statement info = repository.getBlockReceipts(height).toFuture()
            .get();

        Assertions.assertNotNull(info);

        Assertions.assertEquals(1, info.getAddressResolutionStatements().size());
        Assertions.assertEquals(BigInteger.valueOf(6L),
            info.getAddressResolutionStatements().get(0).getHeight());
        Assertions.assertEquals("SBILTA367K2LX2FEXG5TFWAS7GEFYAGY7QLFBYKC",
            info.getAddressResolutionStatements().get(0).getUnresolved().plain());

        Assertions.assertEquals(1, info.getMosaicResolutionStatement().size());
        Assertions.assertEquals(BigInteger.valueOf(7L),
            info.getMosaicResolutionStatement().get(0).getHeight());
        Assertions.assertEquals(BigInteger.valueOf(9L),
            info.getMosaicResolutionStatement().get(0).getUnresolved().getId());

    }

}
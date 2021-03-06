/*
 * Copyright 2020 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nem.symbol.sdk.infrastructure.okhttp;

import io.nem.symbol.core.utils.MapperUtils;
import io.nem.symbol.sdk.api.MultisigRepository;
import io.nem.symbol.sdk.model.account.Address;
import io.nem.symbol.sdk.model.account.MultisigAccountGraphInfo;
import io.nem.symbol.sdk.model.account.MultisigAccountInfo;
import io.nem.symbol.sdk.model.blockchain.MerkleStateInfo;
import io.nem.symbol.sdk.openapi.okhttp_gson.api.MultisigRoutesApi;
import io.nem.symbol.sdk.openapi.okhttp_gson.invoker.ApiClient;
import io.nem.symbol.sdk.openapi.okhttp_gson.model.MultisigAccountGraphInfoDTO;
import io.nem.symbol.sdk.openapi.okhttp_gson.model.MultisigAccountInfoDTO;
import io.nem.symbol.sdk.openapi.okhttp_gson.model.MultisigDTO;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;

public class MultisigRepositoryOkHttpImpl extends AbstractRepositoryOkHttpImpl
    implements MultisigRepository {

  private final MultisigRoutesApi client;

  public MultisigRepositoryOkHttpImpl(ApiClient apiClient) {
    super(apiClient);
    this.client = new MultisigRoutesApi(apiClient);
  }

  @Override
  public Observable<MultisigAccountInfo> getMultisigAccountInfo(Address address) {
    return call(() -> getClient().getAccountMultisig(address.plain()), this::toMultisigAccountInfo);
  }

  @Override
  public Observable<MerkleStateInfo> getMultisigAccountInfoMerkle(Address address) {
    return call(
        () -> getClient().getAccountMultisigMerkle(address.plain()), this::toMerkleStateInfo);
  }

  @Override
  public Observable<MultisigAccountGraphInfo> getMultisigAccountGraphInfo(Address address) {
    return (call(
        () -> getClient().getAccountMultisigGraph(address.plain()),
        multisigAccountGraphInfoDTOList -> {
          Map<Integer, List<MultisigAccountInfo>> multisigAccountInfoMap = new HashMap<>();
          multisigAccountGraphInfoDTOList.forEach(
              item -> multisigAccountInfoMap.put(item.getLevel(), toMultisigAccountInfo(item)));
          return new MultisigAccountGraphInfo(multisigAccountInfoMap);
        }));
  }

  private List<MultisigAccountInfo> toMultisigAccountInfo(MultisigAccountGraphInfoDTO item) {
    return item.getMultisigEntries().stream()
        .map(this::toMultisigAccountInfo)
        .collect(Collectors.toList());
  }

  private MultisigAccountInfo toMultisigAccountInfo(MultisigAccountInfoDTO info) {
    MultisigDTO dto = info.getMultisig();
    return new MultisigAccountInfo(
        null,
        ObjectUtils.defaultIfNull(info.getMultisig().getVersion(), 1),
        MapperUtils.toAddress(dto.getAccountAddress()),
        dto.getMinApproval(),
        dto.getMinRemoval(),
        dto.getCosignatoryAddresses().stream()
            .map(MapperUtils::toAddress)
            .collect(Collectors.toList()),
        dto.getMultisigAddresses().stream()
            .map(MapperUtils::toAddress)
            .collect(Collectors.toList()));
  }

  public MultisigRoutesApi getClient() {
    return client;
  }
}

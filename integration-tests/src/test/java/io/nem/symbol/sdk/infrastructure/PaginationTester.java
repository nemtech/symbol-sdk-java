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
package io.nem.symbol.sdk.infrastructure;

import io.nem.symbol.sdk.api.OrderBy;
import io.nem.symbol.sdk.api.Page;
import io.nem.symbol.sdk.api.PaginationStreamer;
import io.nem.symbol.sdk.api.SearchCriteria;
import io.nem.symbol.sdk.api.Searcher;
import io.nem.symbol.sdk.model.Stored;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;

public class PaginationTester<E extends Stored, C extends SearchCriteria<C>> {

  private final Supplier<C> criteriaFactory;
  private final Searcher<E, C> searcher;
  private final PaginationStreamer<E, C> streamer;
  private final TestHelper helper;

  public PaginationTester(Supplier<C> criteriaFactory, Searcher<E, C> searcher) {
    this.helper = new TestHelper();
    this.searcher = searcher;
    this.criteriaFactory = criteriaFactory;
    this.streamer = new PaginationStreamer<>(searcher);
  }

  public static <E extends Stored> void sameEntities(List<E> firstList, List<E> secondList) {
    Assertions.assertEquals(
        firstList.stream().map(Stored::getRecordId).collect(Collectors.toList()),
        secondList.stream().map(Stored::getRecordId).collect(Collectors.toList()));
  }

  public Page<E> basicTestSearch(Integer pageSizeParam) {
    int pageSize = ObjectUtils.firstNonNull(pageSizeParam, 20);
    C criteria = this.criteriaFactory.get();
    criteria.pageSize(pageSizeParam);
    Page<E> page = helper.get(searcher.search(criteria));
    assertPageData(1, pageSize, page);

    C criteriaLasPage = this.criteriaFactory.get();
    criteriaLasPage.pageSize(pageSizeParam);
    criteriaLasPage.pageNumber(Integer.MAX_VALUE);
    Page<E> lastPage = helper.get(searcher.search(criteriaLasPage));
    assertPageData(criteriaLasPage.getPageNumber(), pageSize, lastPage);
    return page;
  }

  private void assertPageData(int pageNumber, int pageSize, Page<E> page) {
    Assertions.assertEquals(pageNumber, page.getPageNumber());
    Assertions.assertEquals(pageSize, page.getPageSize());
    // if (page.getTotalPages() > pageNumber) {
    // Assertions.assertTrue(page.getTotalEntries() > pageSize);
    // Assertions.assertEquals(pageSize, page.getData().size());
    // }
    //
    // if (page.getTotalPages() < pageNumber) {
    // Assertions.assertEquals(0, page.getData().size());
    // }
    Assertions.assertTrue(page.getData().size() <= pageSize);
    // Assertions
    // .assertEquals(page.getTotalPages(), (int) Math.ceil((double)
    // page.getTotalEntries() / page.getPageSize()));
  }

  public void usingBigPageSize() {
    C criteria = this.criteriaFactory.get();
    int pageLimit = 100;
    int pageSize = 101;
    criteria.pageSize(pageSize);
    Page<E> page = helper.get(searcher.search(criteria));
    // if (page.getTotalPages() > 1) {
    // Assertions.assertEquals(1, page.getPageNumber());
    // Assertions.assertEquals(pageLimit, page.getPageSize());
    // Assertions.assertEquals(pageLimit, page.getData().size());
    // }
  }

  public void searchOrderByIdDesc() {
    C criteria = this.criteriaFactory.get();
    criteria.setOrder(OrderBy.DESC);

    List<E> blocks = helper.get(streamer.search(criteria).toList().toObservable());
    List<E> sorted =
        blocks.stream()
            .sorted(Comparator.comparing(this::getDatabaseId).reversed())
            .collect(Collectors.toList());
    Assertions.assertEquals(blocks, sorted);
  }

  void searchOrderByIdAsc() {
    C criteria = this.criteriaFactory.get();
    criteria.setOrder(OrderBy.ASC);
    List<E> blocks = helper.get(streamer.search(criteria).toList().toObservable());
    List<E> sorted =
        blocks.stream()
            .sorted(Comparator.comparing(this::getDatabaseId))
            .collect(Collectors.toList());
    Assertions.assertEquals(blocks, sorted);
  }

  private String getDatabaseId(Stored stored) {
    return stored.getRecordId().orElseThrow(IllegalStateException::new);
  }
}

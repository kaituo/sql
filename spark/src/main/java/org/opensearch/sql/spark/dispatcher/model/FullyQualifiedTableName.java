/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.sql.spark.dispatcher.model;

import java.util.Arrays;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Fully Qualified Table Name in the query provided. */
@Data
@NoArgsConstructor
public class FullyQualifiedTableName {
  private String datasourceName;
  private String schemaName;
  private String tableName;
  private String fullyQualifiedName;

  /**
   * This constructor also takes care of logic to split the fully qualified name into respective
   * pieces. If the name has more than three parts, first part is assigned tp datasource name,
   * second is schemaName, third is tableName If there are only two parts, first part is assigned to
   * schema name and second to table. If there is only one part it is assigned to table Name.
   *
   * @param fullyQualifiedName fullyQualifiedName.
   */
  public FullyQualifiedTableName(String fullyQualifiedName) {
    this.fullyQualifiedName = fullyQualifiedName;
    String[] parts = fullyQualifiedName.split("\\.");
    if (parts.length >= 3) {
      datasourceName = parts[0];
      schemaName = parts[1];
      tableName = String.join(".", Arrays.copyOfRange(parts, 2, parts.length));
    } else if (parts.length == 2) {
      schemaName = parts[0];
      tableName = parts[1];
    } else if (parts.length == 1) {
      tableName = parts[0];
    }
  }
}

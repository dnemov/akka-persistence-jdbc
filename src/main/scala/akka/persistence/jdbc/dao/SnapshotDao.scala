/*
 * Copyright 2018 Dmitry Nemov
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

package akka.persistence.jdbc.dao

import akka.persistence.jdbc.snapshot.JdbcSnapshotStore.SerializationResult

import scala.concurrent.Future

trait SnapshotDao {
  def deleteAllSnapshots(persistenceId: String): Future[Unit]

  def deleteUpToMaxSequenceNr(persistenceId: String, maxSequenceNr: Long): Future[Unit]

  def deleteUpToMaxTimestamp(persistenceId: String, maxTimestamp: Long): Future[Unit]

  def deleteUpToMaxSequenceNrAndMaxTimestamp(persistenceId: String, maxSequenceNr: Long, maxTimestamp: Long): Future[Unit]

  def snapshotForMaxSequenceNr(persistenceId: String): Future[Option[SerializationResult]]

  def snapshotForMaxTimestamp(persistenceId: String, timestamp: Long): Future[Option[SerializationResult]]

  def snapshotForMaxSequenceNr(persistenceId: String, sequenceNr: Long): Future[Option[SerializationResult]]

  def snapshotForMaxSequenceNrAndMaxTimestamp(persistenceId: String, sequenceNr: Long, timestamp: Long): Future[Option[SerializationResult]]

  def delete(persistenceId: String, sequenceNr: Long): Future[Unit]

  def save(persistenceId: String, sequenceNr: Long, timestamp: Long, serializationResult: SerializationResult): Future[Unit]
}

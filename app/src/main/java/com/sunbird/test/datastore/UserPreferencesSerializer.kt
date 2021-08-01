package com.sunbird.test.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream


/**
 * 作者：王豪
 * 日期：2021/8/1
 * 描述：<必填>
 */
object UserPreferencesSerializer : Serializer<UserPreferences> {
  /**
   * Value to return if there is no data on disk.
   */
  override val defaultValue: UserPreferences
    get() = UserPreferences.getDefaultInstance()

  /**
   * Unmarshal object from stream.
   *
   * @param input the InputStream with the data to deserialize
   */
  override suspend fun readFrom(input: InputStream): UserPreferences {
    return UserPreferences.parseFrom(input)
  }

  /**
   *  Marshal object to a stream. Closing the provided OutputStream is a no-op.
   *
   *  @param t the data to write to output
   *  @output the OutputStream to serialize data to
   */
  override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
    t.writeTo(output)
  }
}
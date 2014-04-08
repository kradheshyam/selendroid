/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.util;

import android.util.Log;

import java.lang.UnsatisfiedLinkError;

public class SelendroidLogger {
  public static void error(String message, Exception e) {
    error(message + ": " + e.getMessage());
  }

  private static String formatMessage(String message) {
    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
    return String.format("%s#%s:%d - %s", stackTraceElement.getClassName(),
        stackTraceElement.getMethodName(), stackTraceElement.getLineNumber(), message);
  }

  private static boolean isLoggable(int logLevel) {
    try {
      return Log.isLoggable("SELENDROID", logLevel);
    } catch (UnsatisfiedLinkError e) { // this lets the tests run on the jvm as well.
      return false;
    }
  }

  public static void debug(String message) {
    if (isLoggable(Log.DEBUG)) {
      Log.d("SELENDROID", formatMessage(message));
    }
  }

  public static void error(String message) {
    if (isLoggable(Log.ERROR)) {
      Log.e("SELENDROID", formatMessage(message));
    }
  }

  public static void info(String message) {
    if (isLoggable(Log.INFO)) {
      Log.i("SELENDROID", formatMessage(message));
    }
  }

  public static void warning(String message) {
    if (isLoggable(Log.WARN)) {
      Log.w("SELENDROID", formatMessage(message));
    }
  }

  public static void verbose(String message) {
    if (isLoggable(Log.VERBOSE)) {
      Log.v("SELENDROID", formatMessage(message));
    }
  }
}

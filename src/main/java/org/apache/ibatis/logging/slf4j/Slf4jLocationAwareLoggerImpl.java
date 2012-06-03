/*
 *    Copyright 2009-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.logging.slf4j;

import org.apache.ibatis.logging.Log;
import org.slf4j.spi.LocationAwareLogger;

class Slf4jLocationAwareLoggerImpl implements Log {
  
  private static final String FQCN = Slf4jImpl.class.getName();

  private LocationAwareLogger logger;

  Slf4jLocationAwareLoggerImpl(LocationAwareLogger logger) {
    this.logger = logger;
  }

  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  public void error(String s, Throwable e) {
    logger.log(null, FQCN, LocationAwareLogger.ERROR_INT, s, null, e);
  }

  public void error(String s) {
    logger.log(null, FQCN, LocationAwareLogger.ERROR_INT, s, null, null);
  }

  public void debug(String s) {
    logger.log(null, FQCN, LocationAwareLogger.DEBUG_INT, s, null, null);
  }

  public void trace(String s) {
    logger.log(null, FQCN, LocationAwareLogger.TRACE_INT, s, null, null);
  }

  public void warn(String s) {
    logger.log(null, FQCN, LocationAwareLogger.WARN_INT, s, null, null);
  }

}
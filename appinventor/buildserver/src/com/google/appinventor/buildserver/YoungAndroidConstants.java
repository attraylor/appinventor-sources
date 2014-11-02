// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.buildserver;

/**
 * Constants related to Young Android projects and files.
 *
 * @author lizlooney@google.com (Liz Looney)
 */
public class YoungAndroidConstants {
  private YoungAndroidConstants() {
  }

  /**
   * The filename extension for the file which contains the component
   * properties information generated by the ODE client.
   */
  public static final String FORM_PROPERTIES_EXTENSION = ".scm";

    /**
   * The filename extension for the file which contains the component
   * properties information generated by the ODE client.
   */
  public static final String TASK_PROPERTIES_EXTENSION = ".tsk";

  /**
   * The filename extension for the file which contains the Scheme
   * code which represents the mobile application itself.
   */
  public static final String YAIL_EXTENSION = ".yail";

  /**
   * The filename extension for the file which contains block metadata to be
   * consumed by codeblocks.
   */
  public static final String CODEBLOCKS_SOURCE_EXTENSION = ".blk";

  /**
   * The filename for a project's keystore, relative to the directory that contains the
   * project.properties file.
   */
  public static final String PROJECT_KEYSTORE_LOCATION = "android.keystore";
}

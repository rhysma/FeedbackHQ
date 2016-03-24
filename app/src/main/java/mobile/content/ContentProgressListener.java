//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.6
//
package mobile.content;

/** Handles progress updates for content being added using the ContentManager. */
public interface ContentProgressListener {

    /**
     * Called on the main thread when the file completes being downloaded and added to the cache.
     *
     * @param contentItem the File reference or remote meta data.
     */
    void onSuccess(ContentItem contentItem);

    /**
     * Called on the main thread when the progress changes for an in-progress transfer.
     *
     * @param filePath the relative path and file name.
     * @param isWaiting flag indicating whether this transfer is suspended and waiting.
     * @param bytesCurrent the number of bytes transferred so far.
     * @param bytesTotal the number of bytes total for this file.
     */
    void onProgressUpdate(String filePath, boolean isWaiting, long bytesCurrent, long bytesTotal);

    /**
     * Called on the main thread when an error occurs downloading a file.
     * @param filePath the relative file path and file name.
     * @param ex the exception that occurred.
     */
    void onError(String filePath, Exception ex);
}

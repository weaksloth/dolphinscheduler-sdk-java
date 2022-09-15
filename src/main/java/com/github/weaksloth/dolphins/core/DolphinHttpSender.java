package com.github.weaksloth.dolphins.core;

public interface DolphinHttpSender {

  DolphinResponse sendPostForm(String url, Object formData);

  DolphinResponse sendPostJson(String url, Object jsonData);

  DolphinResponse sendGet();

  DolphinResponse sendPutForm();

  DolphinResponse sendPutJson();

  DolphinResponse sendDelete();
}

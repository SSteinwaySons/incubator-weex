/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;

public class GraphicActionLayout extends BasicGraphicAction {

  private final GraphicPosition mLayoutPosition;
  private final GraphicSize mLayoutSize;

  public GraphicActionLayout(String pageId, String ref, GraphicPosition layoutPosition, GraphicSize layoutSize) {
    super(pageId, ref);
    this.mLayoutPosition = layoutPosition;
    this.mLayoutSize = layoutSize;
  }

  @Override
  public void executeAction() {
    WXComponent component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
    if (component == null) {
      return;
    }

    long start = System.currentTimeMillis();
    component.updateDemission(mLayoutSize, mLayoutPosition);
    WXSDKManager.getInstance().getSDKInstance(getPageId()).callLayoutUpdateDemissionTime(System.currentTimeMillis() - start);

    component.setLayout(component);
    component.setPadding(component.getPadding(), component.getBorder());

    WXSDKManager.getInstance().getSDKInstance(getPageId()).callActionLayoutCount();
  }
}

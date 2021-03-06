// Copyright 2019 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.analysis.config;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.analysis.config.transitions.NoTransition;
import com.google.devtools.build.lib.analysis.config.transitions.NullTransition;
import com.google.devtools.build.lib.analysis.config.transitions.SplitTransition;
import com.google.devtools.build.lib.analysis.config.transitions.TransitionFactory;
import com.google.devtools.build.lib.analysis.config.transitions.TransitionFactory.TransitionFactoryData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link TransitionFactories}. */
@RunWith(JUnit4.class)
public class TransitionFactoriesTest {

  @Test
  public void hostTransition() {
    TransitionFactory<TransitionFactoryData> factory =
        TransitionFactories.of(HostTransition.INSTANCE);
    assertThat(factory).isNotNull();
    assertThat(HostTransition.isInstance(factory)).isTrue();
    assertThat(factory.isHost()).isTrue();
    assertThat(factory.isSplit()).isFalse();
    assertThat(factory.isFinal()).isTrue();
  }

  @Test
  public void noTransition() {
    TransitionFactory<TransitionFactoryData> factory =
        TransitionFactories.of(NoTransition.INSTANCE);
    assertThat(factory).isNotNull();
    assertThat(NoTransition.isInstance(factory)).isTrue();
    assertThat(factory.isHost()).isFalse();
    assertThat(factory.isSplit()).isFalse();
    assertThat(factory.isFinal()).isFalse();
  }

  @Test
  public void nullTransition() {
    TransitionFactory<TransitionFactoryData> factory =
        TransitionFactories.of(NullTransition.INSTANCE);
    assertThat(factory).isNotNull();
    assertThat(NullTransition.isInstance(factory)).isTrue();
    assertThat(factory.isHost()).isFalse();
    assertThat(factory.isSplit()).isFalse();
    assertThat(factory.isFinal()).isTrue();
  }

  @Test
  public void splitTransition() {
    TransitionFactory<TransitionFactoryData> factory =
        TransitionFactories.of(
            (SplitTransition) buildOptions -> ImmutableList.of(buildOptions.clone()));
    assertThat(factory).isNotNull();
    assertThat(factory.isHost()).isFalse();
    assertThat(factory.isSplit()).isTrue();
    assertThat(factory.isFinal()).isFalse();
  }
}

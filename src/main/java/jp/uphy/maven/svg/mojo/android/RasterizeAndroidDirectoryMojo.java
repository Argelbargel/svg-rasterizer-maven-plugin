package jp.uphy.maven.svg.mojo.android;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import jp.uphy.maven.svg.model.OutputDefinition;
import jp.uphy.maven.svg.model.Replacers;
import jp.uphy.maven.svg.mojo.AbstractOutput;
import jp.uphy.maven.svg.mojo.AbstractRasterizeDirectoryMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Collection;

import static jp.uphy.maven.svg.mojo.android.Constants.MOJO_NAME_RASTERIZE_ANDROID_DIRECTORY;


@Mojo(name = MOJO_NAME_RASTERIZE_ANDROID_DIRECTORY, defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class RasterizeAndroidDirectoryMojo extends AbstractRasterizeDirectoryMojo {
    @Parameter
    private Output defaults;

    public RasterizeAndroidDirectoryMojo() {
        super();
    }

    @Override
    protected void validate(Collection outputs) throws MojoFailureException {
        if (outputs != null && outputs.size() != 1) {
            failure("there must be exactly one output-definition in ''{0}''", "outputs");
        }
    }

    @Override
    public Output createDefaults() {
        if (defaults == null) {
            defaults = new Output();
        }

        return defaults;
    }

    @Override
    public OutputDefinition<AbstractOutput> createOutput(String name, int width, int height) {
        return new Output(Replacers.NAME.replace(createDefaults().getName(), name), width, height);
    }
}

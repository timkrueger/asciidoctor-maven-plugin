/*
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.asciidoc.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.asciidoc.maven.http.AsciidoctorHttpServer;

@Mojo(name = "http")
public class AsciidoctorHttpMojo extends AsciidoctorRefreshMojo {
    @Parameter(property = AsciidoctorMaven.PREFIX + "home", required = false, defaultValue = "index")
    protected String home;

    @Override
    protected void doWork() throws MojoFailureException, MojoExecutionException {
        final AsciidoctorHttpServer server = new AsciidoctorHttpServer(getLog(), port, outputDirectory, home);
        server.start();

        super.doWork();

        server.stop();
    }

    public String getHome() {
        return home;
    }

    public void setHome(final String home) {
        this.home = home;
    }
}

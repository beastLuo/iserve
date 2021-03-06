/*
   Copyright ${year}  Knowledge Media Institute - The Open University

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package uk.ac.open.kmi.iserve.sal.model.impl;

import uk.ac.open.kmi.iserve.sal.model.common.URI;
import uk.ac.open.kmi.iserve.sal.model.taxonomy.Category;

public class CategoryImpl extends EntityImpl implements Category {

	private static final long serialVersionUID = 6970758002598278432L;

	private URI parentUri;

	public CategoryImpl() {
		super();
		parentUri = null;
	}

	public CategoryImpl(URI uri, URI parentUri) {
		super(uri);
		setParentUri(parentUri);
	}

	public URI getParentUri() {
		return parentUri;
	}

	public void setParentUri(URI parentUri) {
		this.parentUri = parentUri;
	}

}

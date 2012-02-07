/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.datamodel;

/**
 * Visitor pattern for DisplayableItemNodes
 */
public interface DisplayableItemNodeVisitor<T> {

    T visit(DirectoryNode dn);

    T visit(FileNode fn);

    T visit(ImageNode in);

    T visit(VolumeNode vn);

    T visit(BlackboardArtifactNode ban);

    T visit(ArtifactTypeNode atn);

    T visit(ExtractedContentNode ecn);

    T visit(FileSearchFilterNode fsfn);

    T visit(SearchFiltersNode sfn);

    /**
     * Visitor with an implementable default behavior for all types. Override
     * specific visit types to not use the default behavior.
     * @param <T>
     */
    static abstract class Default<T> implements DisplayableItemNodeVisitor<T> {

        /**
         * Default visit for all types
         * @param c
         * @return
         */
        protected abstract T defaultVisit(DisplayableItemNode c);

        @Override
        public T visit(DirectoryNode dn) {
            return defaultVisit(dn);
        }

        @Override
        public T visit(FileNode fn) {
            return defaultVisit(fn);
        }

        @Override
        public T visit(ImageNode in) {
            return defaultVisit(in);
        }

        @Override
        public T visit(VolumeNode vn) {
            return defaultVisit(vn);
        }

        @Override
        public T visit(BlackboardArtifactNode ban){
            return defaultVisit(ban);
        }

        @Override
        public T visit(ArtifactTypeNode atn){
            return defaultVisit(atn);
        }

        @Override
        public T visit(ExtractedContentNode ecn){
            return defaultVisit(ecn);
        }

        @Override
        public T visit(FileSearchFilterNode fsfn){
            return defaultVisit(fsfn);
        }

        @Override
        public T visit(SearchFiltersNode sfn){
            return defaultVisit(sfn);
        }
    }
}
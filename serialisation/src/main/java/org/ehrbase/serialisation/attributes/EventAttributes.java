/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;
/**
 * populate the attributes for RM Event, in particular timing information
 */
public class EventAttributes extends LocatableAttributes {

    public EventAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    public Map<String, Object> toMap(Event<?> event){

        if (event instanceof IntervalEvent) {
            IntervalEvent<?> intervalEvent = (IntervalEvent<?>) event;
            if (intervalEvent.getWidth() != null)
                map = toMap(TAG_WIDTH, intervalEvent.getWidth(), event.getName());
            if (intervalEvent.getMathFunction() != null)
                map = toMap(TAG_MATH_FUNCTION, intervalEvent.getMathFunction(), event.getName());
        }


        if (event.getTime() != null) {
            map = toMap(TAG_TIME, event.getTime(), event.getName());
        }

        map =  super.toMap(event);

        return map;
    }
}

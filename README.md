# Android_TestTask

I think it would be proper to not disclose details of the task. But here is some info for people who are in the context of task and might be assessing it.

So it turned out that the given time would not be enough to cover every aspect of this feature. For me, might be.

These are the points that have to be covered:
1. Nice Architecture, following guidelines
2. Following details of the tech task
3. The working task itself
4. Proper usage of the details of the platform.

For the given time I opted in for the following strategy. If I went for doing the BroadcastReceiver first and thus fulfilling item 3 first, I would be taking a risk of spending too much time for that and not covering the rest of items. Because let's be honest it could not work from attempt 1.

Instead, I went for own belief that doing arch first is better. Because fulfilling the working feature objective is easier from there in long term plus there was a small chance that it'll compile and instantly work. On the other hand, doing an ad-hoc implementation makes it difficult to bring in proper arch later so less suitable for real world tasks.

I began with modeling and interfaces. It looked well. Mostly, this assignment follows latest google advices on architecture. It appears to be working.

These are the items that didn't work:
1. There was not enough time to complete the notification code. I'm not sure it's replaced per requirements. ID has to be assigned to achieve that if I'm not mistaken.
2. The broadast receiver didn't work. My plan was to debug that last with adb. But adb didn't work for security reasons.

What went well:
1. Architecture and structure showcase.
2. Naming, SOLID, components breakdown.
3. Decent platform use code.
4. Data and flow modeling.

What didn't went well:
1. I've prepared a template for compose. The ask was to do xml. All my experience is xml, I've only just started learning compose. But gradle reconfiguration would have taken long.
2. Kotlin coroutines are misused. I used RxKotlin successfully for years in prod. Less experienced in coroutines. But I checked the dataflow, it worked.
3. Certain pieces of code are better detailed then rest. All stuff has to be covered in interfaces, etc.
4. If I was to meet deadline, I wouldn't have a chance to produce all this text. 
5. The asked usecase does not work in this impl. But must be close.

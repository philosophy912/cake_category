import Mock from 'mockjs';
import { basicGetAll } from '@/resources/url.js';
const Random = Mock.Random;
const capacityType = ['克', '千克', '个', '毫升', '升', '片']



Mock.mock(`${basicGetAll}`, 'post', Mock.mock({
    'list|10': [{
        'id|+1': 1,
        'name|+1': Random.cparagraph(0, 10),
        'capacity|1-100': 1,
        'capacityType|+1': capacityType,
        'price|1-100.0-9': 1
    }]
}));

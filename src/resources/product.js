export const material = [
    {
        name: '总统黄油卷',
        capacity: 250,
        capacityType: '克',
        price: 34.6,
		pricePerCapacity: 3.15
    },
    {
        name: '安佳大黄油',
        capacity: 5000,
        capacityType: '克',
        price: 34.0,
		pricePerCapacity: 5.15
    }
];

export const basicProduct = [
    {
        name: '曲奇',
        unit: '个',
        count: 15,
        materials: [
            {
                name: '安佳大黄油',
                count: 45,
                type: '原材料',
                value: 2,
                price: 15,
                disabled: true
            },
            {
                name: '安佳大黄油',
                count: 45,
                type: '原材料',
                value: 2,
                price: 1.5,
                disabled: true
            }
        ]
    },
    {
        name: '海绵杯子蛋糕胚',
        unit: '个',
        count: 15,
        totalPrice: 34.0,
        materials: [
            {
                name: '安佳大黄油',
                count: 45,
                totalPrice: 3.06,
                type: '原材料',
                value: 2,
                price: 5.5,
                disabled: true
            },
            {
                name: '安佳大黄油',
                count: 45,
                totalPrice: 3.06,
                type: '原材料',
                value: 2,
                price: 12,
                disabled: true
            }
        ]
    }
];

export const middleProduct = [
    {
        name: '曲奇',
        unit: '个',
        count: 15,
        totalPrice: 34.6,
        products: [
            {
                name: '曲奇',
                count: 1,
                totalPrice: 3.06,
                type: '基础产品',
                value: 2,
                price: 17,
                disabled: true
            },
            {
                name: '布丁',
                count: 2,
                totalPrice: 44.23,
                type: '基础产品',
                value: 2,
                price: 16,
                disabled: true
            }
        ],
        materials: [
            {
                name: '安佳大黄油',
                count: 45,
                totalPrice: 3.06,
                type: '原材料',
                value: 2,
                price: 12,
                disabled: true
            },
            {
                name: '安佳大黄油',
                count: 45,
                totalPrice: 3.06,
                type: '原材料',
                value: 2,
                price: 18,
                disabled: true
            }
        ]
    }
];

export const options = [
    {
        value: 1,
        label: '总统黄油卷'
    },
    {
        value: 2,
        label: '安佳大黄油'
    },
    {
        value: 3,
        label: '安佳奶油'
    },
    {
        value: 4,
        label: '韩国细砂糖'
    }
]
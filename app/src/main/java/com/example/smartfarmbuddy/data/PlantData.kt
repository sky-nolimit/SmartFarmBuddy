package com.example.smartfarmbuddy.data

import com.example.smartfarmbuddy.R

data class PlantCategory(
    val name: String,
    val plants: List<Plant>
)

data class Plant(
    val name: String,
    val description: String,
    val careTips: String,
    val imageResId: Int = R.drawable.cactus,
    val waterFrequencyDays: Int = 7,
    val loosenFrequencyDays: Int = 14,
    val fertilizeFrequencyDays: Int = 30
)

fun findPlantByName(name: String): Plant? {
    if (name.isEmpty()) return null
    return PlantDataSource.categories.flatMap { it.plants }.find { it.name == name }
}

object PlantDataSource {
    val categories = listOf(
        PlantCategory(
            name = "乔木",
            plants = listOf(
                Plant(
                    name = "酒瓶兰",
                    description = "酒瓶兰是一种常绿小乔木，原产于墨西哥东南部。其最大特色在于基部膨大的茎干，形似酒瓶，极具观赏性。叶片细长下垂，呈丝状，优雅飘逸。酒瓶兰生命力顽强，适合室内外栽培。",
                    careTips = "光照：喜阳光充足的环境，也耐半阴，夏季需适当遮阴。温度：适宜生长温度为15-28℃，冬季需保持5℃以上。浇水：耐旱能力强，生长期保持盆土微湿，冬季减少浇水，避免积水导致烂根。土壤：喜欢疏松肥沃、排水良好的沙质土壤。施肥：生长季节每月施一次稀薄液肥，冬季停止施肥。",
                    imageResId = R.drawable.ponytail_palm,
                    waterFrequencyDays = 10,
                    loosenFrequencyDays = 20,
                    fertilizeFrequencyDays = 45
                ),
                Plant(
                    name = "橡皮树",
                    description = "橡皮树又名印度榕、印度橡皮树，是桑科榕属的常绿乔木。叶片厚实光亮，呈椭圆形，颜色深绿，富有光泽。橡皮树株型优美，是常见的室内观叶植物，能有效净化空气。",
                    careTips = "光照：喜阳光充足的环境，但忌强光直射，夏季需遮阴。温度：适宜生长温度为20-30℃，冬季不低于5℃。浇水：保持盆土湿润，夏季可适当增加浇水，冬季减少。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每月施一次复合肥，冬季停止施肥。修剪：定期修剪可以保持株型美观。",
                    imageResId = R.drawable.rubber_plant,
                    waterFrequencyDays = 5,
                    loosenFrequencyDays = 15,
                    fertilizeFrequencyDays = 30
                ),
                Plant(
                    name = "南洋杉",
                    description = "南洋杉是南洋杉科南洋杉属的常绿乔木，原产于大洋洲。树形优美，呈塔状，枝叶繁茂，是世界著名的观赏树种。南洋杉木材优良，可用于建筑和家具制作。",
                    careTips = "光照：喜阳光充足的环境，幼苗耐阴。温度：适宜生长温度为18-25℃，冬季需保持10℃以上。浇水：保持盆土湿润，夏季多浇水，冬季控制浇水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每2-3周施一次稀薄液肥。",
                    imageResId = R.drawable.araucaria,
                    waterFrequencyDays = 7,
                    loosenFrequencyDays = 21,
                    fertilizeFrequencyDays = 45
                ),
                Plant(
                    name = "发财树",
                    description = "发财树又名马拉巴栗、中美木棉，是木棉科瓜栗属的常绿小乔木。因名称吉祥而深受喜爱，是常见的室内盆栽植物。发财树叶片宽大，呈掌状复叶，株型优美，寓意财源广进。",
                    careTips = "光照：喜温暖湿润的环境，忌强光直射，耐阴性较强。温度：适宜生长温度为20-30℃，冬季需保持10℃以上。浇水：浇水不宜过多，保持盆土微湿即可，忌积水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每月施一次稀薄液肥，冬季停止施肥。",
                    imageResId = R.drawable.money_tree,
                    waterFrequencyDays = 7,
                    loosenFrequencyDays = 14,
                    fertilizeFrequencyDays = 30
                ),
                Plant(
                    name = "铁树",
                    description = "铁树又名苏铁，是苏铁科苏铁属的常绿木本植物，是一种古老的裸子植物。叶片坚硬如铁，呈羽状复叶，四季常青。铁树生长缓慢，但寿命极长，可达数百年。",
                    careTips = "光照：喜阳光充足的环境，也耐半阴。温度：适宜生长温度为20-30℃，冬季需保持5℃以上。浇水：耐旱能力强，浇水不宜过多，保持盆土微湿即可。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每月施一次复合肥，冬季停止施肥。",
                    imageResId = R.drawable.cycad,
                    waterFrequencyDays = 15,
                    loosenFrequencyDays = 30,
                    fertilizeFrequencyDays = 60
                ),
                Plant(
                    name = "荷兰铁",
                    description = "荷兰铁又称巨丝兰、象脚丝兰，是龙舌兰科丝兰属的常绿木本植物。叶片坚挺翠绿，呈剑形，向上生长，极具观赏性。荷兰铁适应性强，是优良的室内观叶植物。",
                    careTips = "光照：喜阳光充足的环境，也耐阴。温度：适宜生长温度为15-25℃，冬季需保持5℃以上。浇水：耐旱能力强，生长期保持盆土微湿，冬季减少浇水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每2-3周施一次稀薄液肥。",
                    imageResId = R.drawable.dutch_iron,
                    waterFrequencyDays = 10,
                    loosenFrequencyDays = 21,
                    fertilizeFrequencyDays = 45
                ),
                Plant(
                    name = "巴西木",
                    description = "巴西木又名香龙血树、巴西铁树，是龙舌兰科龙血树属的常绿乔木。叶片宽大，呈剑形，颜色深绿，富有光泽。巴西木是著名的室内观叶植物，能有效净化空气，吸收甲醛等有害物质。",
                    careTips = "光照：喜温暖湿润的环境，忌阳光直射，耐阴性较强。温度：适宜生长温度为20-30℃，冬季需保持10℃以上。浇水：保持盆土湿润，夏季可适当增加浇水。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每月施一次复合肥。",
                    imageResId = R.drawable.brazilian_wood,
                    waterFrequencyDays = 7,
                    loosenFrequencyDays = 14,
                    fertilizeFrequencyDays = 30
                )
            )
        ),
        PlantCategory(
            name = "灌木",
            plants = listOf(
                Plant(
                    name = "月季",
                    description = "月季被誉为花中皇后，是蔷薇科蔷薇属的落叶或半常绿灌木。花色丰富，有红、粉、黄、白等多种颜色，花型多样，花期长，是世界著名的观赏花卉。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要6小时光照。温度：适宜生长温度为15-25℃。浇水：保持盆土湿润，忌积水，夏季多浇水。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每10-15天施一次稀薄液肥，开花期增施磷钾肥。修剪：定期修剪可以促进开花，保持株型美观。",
                    imageResId = R.drawable.rose_bush,
                    waterFrequencyDays = 3,
                    loosenFrequencyDays = 10,
                    fertilizeFrequencyDays = 15
                ),
                Plant(
                    name = "杜鹃",
                    description = "杜鹃花又名映山红，是杜鹃花科杜鹃花属的常绿或落叶灌木。花色艳丽，有红、粉、白、紫等多种颜色，品种繁多，是著名的观赏花卉。杜鹃在中国分布广泛，是中国十大名花之一。",
                    careTips = "光照：喜半阴环境，忌强光直射，夏季需遮阴。温度：适宜生长温度为15-20℃，冬季需保持5℃以上。浇水：保持盆土湿润，忌积水，水质以酸性为宜。土壤：喜欢疏松肥沃、排水良好的酸性土壤。施肥：生长季节每15-20天施一次稀薄液肥，忌浓肥。",
                    imageResId = R.drawable.azalea,
                    waterFrequencyDays = 4,
                    loosenFrequencyDays = 12,
                    fertilizeFrequencyDays = 20
                ),
                Plant(
                    name = "牡丹",
                    description = "牡丹是毛茛科芍药属的落叶灌木，是中国传统名花，被誉为花中之王。花大色艳，品种繁多，有红、粉、白、黄、紫等多种颜色，花型端庄典雅，香气浓郁。",
                    careTips = "光照：喜阳光充足的环境，也耐半阴。温度：适宜生长温度为15-25℃，耐寒性较强。浇水：忌积水，保持盆土微湿即可，雨后及时排水。土壤：喜欢疏松肥沃、排水良好的中性或微碱性土壤。施肥：春季萌芽后和花谢后各施一次肥。",
                    imageResId = R.drawable.peony,
                    waterFrequencyDays = 5,
                    loosenFrequencyDays = 15,
                    fertilizeFrequencyDays = 30
                ),
                Plant(
                    name = "茉莉",
                    description = "茉莉花是木犀科素馨属的常绿灌木或藤本植物。花朵洁白芳香，花期长，是著名的香花植物。茉莉花不仅具有观赏价值，还可用于制作花茶和香精。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要4小时光照。温度：适宜生长温度为20-30℃，冬季需保持5℃以上。浇水：保持盆土湿润，夏季可适当增加浇水。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每7-10天施一次稀薄液肥，开花期增施磷钾肥。",
                    imageResId = R.drawable.jasmine,
                    waterFrequencyDays = 3,
                    loosenFrequencyDays = 10,
                    fertilizeFrequencyDays = 15
                )
            )
        ),
        PlantCategory(
            name = "草本",
            plants = listOf(
                Plant(
                    name = "绿萝",
                    description = "绿萝是天南星科麒麟叶属的常绿草本植物，是最常见的室内观叶植物之一。叶片翠绿光亮，呈心形，枝条柔软下垂，易于养护。绿萝能有效净化空气，吸收甲醛、苯等有害物质。",
                    careTips = "光照：耐阴性强，忌阳光直射，适合放在散射光充足的地方。温度：适宜生长温度为15-30℃，冬季需保持10℃以上。浇水：保持盆土湿润，夏季可适当增加浇水，忌积水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每月施一次稀薄液肥。",
                    imageResId = R.drawable.pothos,
                    waterFrequencyDays = 4,
                    loosenFrequencyDays = 12,
                    fertilizeFrequencyDays = 20
                ),
                Plant(
                    name = "吊兰",
                    description = "吊兰是百合科吊兰属的常绿草本植物。叶片细长柔软，呈条形，四季常青。吊兰是优良的室内净化植物，能吸收空气中的甲醛、苯等有害物质，还能分解复印机、打印机释放的有害气体。",
                    careTips = "光照：喜温暖湿润的环境，耐阴性强，忌强光直射。温度：适宜生长温度为15-25℃，冬季需保持5℃以上。浇水：保持盆土湿润，夏季可适当增加浇水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每15-20天施一次稀薄液肥。",
                    imageResId = R.drawable.spider_plant,
                    waterFrequencyDays = 5,
                    loosenFrequencyDays = 14,
                    fertilizeFrequencyDays = 25
                ),
                Plant(
                    name = "芦荟",
                    description = "芦荟是阿福花科芦荟属的常绿草本植物。叶片肥厚多汁，呈剑形，边缘有刺。芦荟不仅具有观赏价值，还具有药用功效，可用于治疗皮肤炎症、烧伤等。",
                    careTips = "光照：喜阳光充足的环境，也耐半阴。温度：适宜生长温度为20-30℃，冬季需保持5℃以上。浇水：耐旱能力强，浇水不宜过多，保持盆土微湿即可，忌积水。土壤：喜欢疏松肥沃、排水良好的沙质土壤。施肥：生长季节每月施一次稀薄液肥，冬季停止施肥。",
                    imageResId = R.drawable.aloe_vera,
                    waterFrequencyDays = 10,
                    loosenFrequencyDays = 21,
                    fertilizeFrequencyDays = 45
                ),
                Plant(
                    name = "仙人掌",
                    description = "仙人掌是仙人掌科仙人掌属的多年生草本植物，是耐旱植物的代表。形态多样，有的呈球形，有的呈柱状，有的呈扁平状。仙人掌生命力顽强，适合干旱地区生长。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要6小时光照。温度：适宜生长温度为20-30℃，冬季需保持5℃以上。浇水：极耐旱，浇水宜少不宜多，干透浇透，冬季几乎不需要浇水。土壤：喜欢疏松肥沃、排水良好的沙质土壤。施肥：生长季节每2-3周施一次稀薄液肥，冬季停止施肥。",
                    imageResId = R.drawable.cactus,
                    waterFrequencyDays = 15,
                    loosenFrequencyDays = 30,
                    fertilizeFrequencyDays = 60
                )
            )
        ),
        PlantCategory(
            name = "花卉",
            plants = listOf(
                Plant(
                    name = "玫瑰",
                    description = "玫瑰是蔷薇科蔷薇属的落叶灌木，花象征爱情，是世界著名的观赏花卉。花色丰富，有红、粉、黄、白等多种颜色，花型优美，香气浓郁。玫瑰不仅具有观赏价值，还可用于制作花茶和香精。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要6小时光照。温度：适宜生长温度为15-25℃。浇水：保持盆土湿润，忌积水，夏季多浇水。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每10-15天施一次稀薄液肥，开花期增施磷钾肥。修剪：定期修剪可以促进开花。",
                    imageResId = R.drawable.rose,
                    waterFrequencyDays = 3,
                    loosenFrequencyDays = 10,
                    fertilizeFrequencyDays = 15
                ),
                Plant(
                    name = "菊花",
                    description = "菊花是菊科菊属的多年生草本植物，是中国传统名花。品种繁多，色彩丰富，有红、黄、白、紫等多种颜色，花型多样。菊花不仅具有观赏价值，还具有药用和食用价值。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要6小时光照。温度：喜凉爽气候，适宜生长温度为15-20℃，忌高温多湿。浇水：保持盆土湿润，忌积水。土壤：喜欢疏松肥沃、排水良好的微酸性土壤。施肥：生长季节每10-15天施一次稀薄液肥，花蕾期增施磷钾肥。",
                    imageResId = R.drawable.chrysanthemum,
                    waterFrequencyDays = 4,
                    loosenFrequencyDays = 12,
                    fertilizeFrequencyDays = 20
                ),
                Plant(
                    name = "兰花",
                    description = "兰花是兰科兰属的多年生草本植物，被誉为花中君子。花香清幽，花型优美，品种繁多。兰花在中国文化中具有重要地位，是中国十大名花之一。",
                    careTips = "光照：喜半阴环境，忌强光直射，适合放在散射光充足的地方。温度：适宜生长温度为15-25℃，冬季需保持5℃以上。浇水：保持盆土湿润，忌积水，水质以酸性为宜。土壤：喜欢疏松肥沃、排水良好的腐殖质土壤。施肥：生长季节每15-20天施一次稀薄液肥，忌浓肥。",
                    imageResId = R.drawable.orchid,
                    waterFrequencyDays = 7,
                    loosenFrequencyDays = 21,
                    fertilizeFrequencyDays = 30
                ),
                Plant(
                    name = "荷花",
                    description = "荷花是睡莲科莲属的多年生水生草本植物，是水生花卉的代表。出淤泥而不染，濯清涟而不妖，是中国传统文化中的重要象征。荷花不仅具有观赏价值，莲子和莲藕还可食用。",
                    careTips = "光照：需要充足阳光，每天至少需要6小时光照。温度：适宜生长温度为20-30℃。浇水：水生植物，需要保持适当水深，一般30-60厘米为宜。土壤：喜欢肥沃的塘泥或河泥。施肥：生长季节每月施一次稀薄液肥。",
                    imageResId = R.drawable.lotus,
                    waterFrequencyDays = 1,
                    loosenFrequencyDays = 0,
                    fertilizeFrequencyDays = 30
                )
            )
        ),
        PlantCategory(
            name = "藤本",
            plants = listOf(
                Plant(
                    name = "常春藤",
                    description = "常春藤是五加科常春藤属的常绿攀援藤本植物。叶片形态优美，颜色丰富，有绿色、黄色、白色等多种颜色。常春藤是常见的攀援植物，可用于垂直绿化，也可作为室内盆栽。",
                    careTips = "光照：喜温暖湿润的环境，耐阴性强，忌强光直射。温度：适宜生长温度为15-25℃，冬季需保持5℃以上。浇水：保持盆土湿润，夏季可适当增加浇水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每15-20天施一次稀薄液肥。",
                    imageResId = R.drawable.ivy,
                    waterFrequencyDays = 5,
                    loosenFrequencyDays = 14,
                    fertilizeFrequencyDays = 25
                ),
                Plant(
                    name = "紫藤",
                    description = "紫藤是豆科紫藤属的落叶攀援藤本植物。花穗下垂，花色淡雅，有紫、白等颜色，花期长，极具观赏性。紫藤是优良的园林植物，可用于搭建花架、拱门等。",
                    careTips = "光照：喜阳光充足的环境，也耐半阴。温度：适宜生长温度为15-25℃，耐寒性较强。浇水：保持盆土湿润，忌积水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每15-20天施一次稀薄液肥。修剪：定期修剪可以保持株型美观。",
                    imageResId = R.drawable.wisteria,
                    waterFrequencyDays = 7,
                    loosenFrequencyDays = 14,
                    fertilizeFrequencyDays = 30
                ),
                Plant(
                    name = "葡萄",
                    description = "葡萄是葡萄科葡萄属的落叶攀援藤本植物。果实酸甜可口，营养丰富，是世界著名的水果之一。葡萄不仅可食用，还可用于酿酒和制作葡萄干。",
                    careTips = "光照：喜阳光充足的环境，每天至少需要6小时光照。温度：适宜生长温度为20-30℃。浇水：保持盆土湿润，忌积水，开花结果期适当控制浇水。土壤：喜欢疏松肥沃、排水良好的土壤。施肥：生长季节每10-15天施一次稀薄液肥，开花结果期增施磷钾肥。",
                    imageResId = R.drawable.grape,
                    waterFrequencyDays = 2,
                    loosenFrequencyDays = 10,
                    fertilizeFrequencyDays = 15
                )
            )
        )
    )

    fun getPrepopulateData(): List<Pair<Plant, String>> {
        val result = mutableListOf<Pair<Plant, String>>()
        categories.forEach { category ->
            category.plants.forEach { plant ->
                result.add(Pair(plant, category.name))
            }
        }
        return result
    }
}
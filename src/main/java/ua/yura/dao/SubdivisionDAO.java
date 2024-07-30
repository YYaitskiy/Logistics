package ua.yura.dao;

import org.springframework.stereotype.Component;
import ua.yura.models.Subdivision;

import java.util.List;
import java.util.UUID;

@Component
public class SubdivisionDAO {

    private List<Subdivision> mcdList;
    private List<Subdivision> pumaList;
    private List<Subdivision> uclList;

    public SubdivisionDAO(List<Subdivision> mcdList, List<Subdivision> pumaList, List<Subdivision> uclList) {
        this.mcdList = mcdList;
        this.pumaList = pumaList;
        this.uclList = uclList;

        mcdList.add(new Subdivision(UUID.randomUUID(), "McD ЦУМ", 48, "+38(050) 463-34-01", "Дніпро", "пр.Д Яворницького (пр.Карла Маркса), 52-A"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD 95 квартал", 31, "+38(050) 463-91-16", "Кривий ріг", "пр. Гагаріна, 2"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Глобал Житомир", 62, "+38(050) 386-71-51", "Житомир", "вул. Київська, 77"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Смілянська", 14, "+3(050) 463-91-20", "Черкаси", "вул. Смілянська, 31"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Стрийський", 100, "+38(095) 283-81-37", "Сокільники", "вул. Стрійська, 30-А"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Дружба", 37, "+38(050) 463-44-55", "Львів", "просп. Чорновола, 12"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Перспективна", 130, "+38(050) 923 45 30", "Кропивницький", "вул. Велика Перспективна, 45/9"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Парк", 115, "+38(050) 347-12-15", "Хмельницький", "пр. Миру, 99/2А"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Короленка", 72, "+38(050) 386-09-13", "Рівне", "вул. Борисенка, 1"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Підстанція", 30, "+38(050) 463-91-14", "Дніпро", "пр. Науки, 128"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Мост сіті", 56, "+38(050) 482-98-40", "Дніпро", "вул. Королеви Єлизавети ІІ, 2"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Вокзал Одеса", 19, "+38(050) 463-91-45", "Одеса", "вул.Пантелеймонівська, 17-Б"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Глобал Київ", 63, "+38(050) 386-17-99", "Київ", "вул. Оноре де Бальзака, 2-А"));
        mcdList.add(new Subdivision(UUID.randomUUID(), "McD Опера", 57, "+38(050) 463-16-82", "Львів", "пр. Совбоди, 35"));

        pumaList.add(new Subdivision(UUID.randomUUID(), "Пума - Европа", 61, "+38(056) 786-01-03", "Дніпро", "бул. Європейський, 1-Д, ТЦ “Європа”" ));
        pumaList.add(new Subdivision(UUID.randomUUID(), "Пума - Кінг Кросс", 66, "+38(032) 246 20 67", "с. Сокільники", "вул. Стрийська, 30"));
        pumaList.add(new Subdivision(UUID.randomUUID(), "Пума - Червоний Камінь", 44, "+38(056) 797-62-30", "Дніпро", "вул. Набережно Заводська, 97-Д"));

        uclList.add(new Subdivision(UUID.randomUUID(), "UCL Office", 1, "+38(044) 390-52-00(01,02,03,05,06)", "Київ", "вул. Семена Скляренка, 15"));
    }

    public List<Subdivision> getMcdList() {
        return mcdList;
    }

    public List<Subdivision> getPumaList() {
        return pumaList;
    }

    public List<Subdivision> getUclList() {
        return uclList;
    }
}

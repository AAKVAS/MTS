# MTS
Отчёт по производственной практике, заключающийся в создании модуля мобильного приложения.

# Задача
В сооружениях торговой сети "Макси" есть оборудование, которое подключается к электросети через распределительные щиты. 
Бывают разные модели щитов, которые могут иметь разное число портов для подключения. Оборудование подключается к щитам  с помощью кабелей.
Необходима возможность просматривать данные о подключённом оборудовании к сооружениям. Также должна быть возможность добавлять, изменять и удалять данные.
Отображаемые данные: сооружение, модель щита, его инвентарный номер, порт, к которому подключено устройство, модель кабеля, длина кабеля, IP и MAC адреса устройства.

# Используемые технологии

- Java 8;
- Gradle;
- Dagger 2;
- RxJava 2;
- ORMLite.

# Требования к использованию
Минимальная версия Android для запуска приложения - 7.1 (SDK 25).

# Запуск приложения
В директории `MTS\app\release` лежит файл приложения `app-release.apk`. Перед его установкой необходимо отключить Play защиту.


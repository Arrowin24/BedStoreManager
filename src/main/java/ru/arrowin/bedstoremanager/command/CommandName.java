package ru.arrowin.bedstoremanager.command;

import lombok.AllArgsConstructor;
import lombok.Getter;


//Enum для названий команд
@Getter
@AllArgsConstructor
public enum CommandName {
    START("/start"), // команда для вызова стартового меню
    HELP("/help"), // вывод главного меню
    BACK_TO_MENU("/backToMenu"), // команда для возвращения в главное меню
    CREATE_WORKER("/newWorker"), // Регистрация и изменение данных по рабочим
    LOGIN("/login"), // логирование НЕ ИСПОЛЬЗУЕТСЯ
    CREATE_FURNITURE("/newFurniture"), //добавление новой мебели НЕ ДОДЕЛАНО
    GET_FURNITURE_TODAY("/getFurnitureToday"), //Получение сделанной мебели за день для работника
    GET_SALARY_TODAY("/getSalaryToday"), // Получение заработной платы за день
    GET_ALL_WORKERS("/getAllWorkers"), // Получением списка всех работников
    UNKNOWN("/unknown"), // Сообщение для запроса нерабочих команд
    GET_ALL_WORK_TODAY("/getAllWorkToday"), //ПОлучение сделанной работы за день НЕ ДОДЕЛАНО
    GET_ALL_BEDS("/getAllBeds"), // Вывод списка кроватей для изготовления
    GET_ALL_SMALL_FURNITURE("/getAllSmallFurniture"), //Вывод списка малой мебели для изотовления
    GET_ALL_OTHER_WORK("/getAllOtherWork"), // Вывод списка доступной работы
    ADD_CREATED("/addCreated"), // Промежуточная команда для вызова кнопок для выполненных заказов
    ADD_CREATED_BED("/addCreatedBed"), //Учет сделанной кровати
    ADD_CREATED_OTHER_WORK("/addCreatedOtherWork"), //Учет сделанной другой работы
    ADD_CREATED_SMALL_FURNITURE("/addCreatedSmallFurniture"), //Учет сделанной малой мебели
    GET_SALARY_BY_CURRENT_MONTH("/getSalaryByCurrentMonth"), // Вызов зарплаты зща месяц
    GET_CREATED_TODAY_BEDS("/getCreatedTodayBeds"), //Вызов клавиатуры со списком сделанных сегодня кроватей
    GET_CREATED_TODAY_SMALL_FURNITURE("/getCreatedTodaySf"), //Вызов клавиатуры со списков сделанной сегодня малой мебели
    GET_CREATED_TODAY_OTHER_WORK("/getCreatedTodayOw"), //Вызов клавиатуры со списков сделанной сегодня иной работы
    DELETE_BED("/deleteBed"),  //Команда удаления кровати из списка
    DELETE_SMALL_FURNITURE("/deleteSF"),  //Команда удаления малой мебели
    DELETE_OTHER_WORK("/deleteOW"), //Команда удаления иных работ
    DELETE_FURNITURE("/delete"); //Комадна удаления

    //Идентификатор команды
    private final String commandName;
}

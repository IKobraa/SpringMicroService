const paramName = new URLSearchParams(window.location.search);
const userName = paramName.get('name')
document.getElementById("user-name").innerText = userName;

const paramSurname = new URLSearchParams(window.location.search);
const userSurname = paramSurname.get('surname')
document.getElementById("user-surname").innerText = userSurname;

const paramEmail = new URLSearchParams(window.location.search);
const userEmail = paramEmail.get('email')
document.getElementById("user-email").innerText = userEmail;

const paramDep = new URLSearchParams(window.location.search);
const userDep = paramDep.get('department')
document.getElementById("user-department").innerText = userDep;

const paramSalary = new URLSearchParams(window.location.search);
const userSalary = paramSalary.get('salary')
document.getElementById("user-salary").innerText = userSalary;

const paramSickDays= new URLSearchParams(window.location.search);
const userSickDays= paramSickDays.get('sickDays')
document.getElementById("user-sick-days").innerText = userSickDays;

const paramHolidays = new URLSearchParams(window.location.search);
const userHolidays = paramHolidays.get('holidays')
document.getElementById("user-holidays").innerText = userHolidays;

const paramHourlyWage = new URLSearchParams(window.location.search);
const userHourlyWage = paramHourlyWage.get('hourlyWage')
document.getElementById("user-hourly-wage").innerText = userHourlyWage;

const paramWorkHours = new URLSearchParams(window.location.search);
const userWorkHours = paramWorkHours.get('workHours')
document.getElementById("user-worked-hours").innerText = userWorkHours;

const paramWorkState = new URLSearchParams(window.location.search);
const userWorkState = paramWorkState.get('workState')
document.getElementById("user-work-state").innerText = userWorkState;

const paramLoginTime = new URLSearchParams(window.location.search);
const userLoginTime = paramLoginTime.get('lastLoginTime')
document.getElementById("user-last-login-time").innerText = userLoginTime;
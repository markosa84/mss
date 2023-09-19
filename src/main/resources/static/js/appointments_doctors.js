document.addEventListener("DOMContentLoaded", function () {


  let currentWeekDatesElement;
  const prevWeekButton = document.getElementById("prev-week-button");
  const nextWeekButton = document.getElementById("next-week-button");
  let startDate, endDate;
  // A maximális előre lépés 1 évvel lehet az aktuális dátumhoz képest
  const maxAdvanceYear = new Date().getFullYear() + 1;

  $(document).ready(function () {
    $('#doctor-select').selectpicker('refresh');
    showCurrentWeekAndTable();
    $('#doctor-select').change(function () {
      displayFreeTimeSlots();
    });
  });

  // Az időpontokat tartalmazó objektum
  var appointmentsByDoctorObject = {};

  var appointmentsByDoctorObject = appointmentsByDoctorObject[doctorName];

  // Ellenőrizd, hogy az appointmentsData nem undefined és tartalmaz-e elemeket
  if (appointmentsData && appointmentsData.length > 0) {
    // Beállítjuk az időpontokat a controller által elküldött adatok alapján
    appointmentsData.forEach(function (data) {
      if (!appointmentsByDoctorObject[data.doctor]) {
        appointmentsByDoctorObject[data.doctor] = [];
      }

      appointmentsByDoctorObject[data.doctor].push(data.appointment);
    });
  }

  function showCurrentWeekAndTable() {
    const currentDate = new Date();
    const day = currentDate.getDay();
    const diff = currentDate.getDate() - day + (day === 0 ? -6 : 1);

    startDate = new Date(currentDate);
    startDate.setDate(diff);
    endDate = new Date(startDate);
    endDate.setDate(startDate.getDate() + 6);

    currentWeekDatesElement = document.getElementById("current-week-dates");

    updateWeekDates(startDate, endDate);
    markPastDays();
  }

  function displayFreeTimeSlots() {
    $('.time-slot').removeClass('available');

    var selectedDoctors = $('#doctor-select').val();

    if (selectedDoctors && selectedDoctors.length > 0) {
      selectedDoctors.forEach(function (currentDoctor) {
        const appointmentsForCurrentDoctor = appointmentsByDoctorObject[currentDoctor];

        if (appointmentsForCurrentDoctor) {
          appointmentsForCurrentDoctor.forEach(function (appointment) {
            // Az appointment változó tartalmazza az aktuális időpont adatait
            const startTime = new Date(appointment.startTime);
            const endTime = new Date(appointment.endTime);

            // Most itt megjelenítheted az időpontot az időpontok táblájában az aktuális orvoshoz tartozóan
            // Ehhez először meg kell találni a megfelelő cellát az időpontnak
            const dayOfWeek = startTime.getDay(); // A hét napjának száma (0 - vasárnap, 1 - hétfő, stb.)
            const timeSlotId = `#time-slot-${dayOfWeek}-${startTime.getHours()}-${startTime.getMinutes()}`;

            // Most hozzáadhatsz egy stílust vagy osztályt a megfelelő cellához
            $(timeSlotId).addClass('available');
          });
        }
      });
    } else {
      // Nincsenek kiválasztott orvosok, tehát minden időpont szabad
      $('.time-slot').addClass('available');
    }
  }

  function updateWeekDates(start, end) {
    const startDateString = start.toLocaleDateString("hu-HU");
    const endDateString = end.toLocaleDateString("hu-HU");
    currentWeekDatesElement.textContent = `Aktuális hét: ${startDateString} - ${endDateString}`;
  }

  function markPastDays() {
    const currentDate = new Date();
    const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
    const currentDayIndex = currentDate.getDay();

    for (let i = 0; i < currentDayIndex; i++) {
      $(`.${days[i]}-column .time-slot`).addClass('inactive');
    }

    var selectedDoctors = $('#doctor-select').val();

    if (selectedDoctors && selectedDoctors.length > 0) {
      const currentDayColumn = $(`.${days[currentDayIndex]}-column`);
      currentDayColumn.find('.time-slot.inactive').removeClass('inactive');
    }
  }

  if (prevWeekButton) {
    prevWeekButton.addEventListener("click", function () {
      const currentDate = new Date();
      if (startDate > currentDate) {
        startDate.setDate(startDate.getDate() - 7);
        endDate.setDate(endDate.getDate());

  }

});
}
});
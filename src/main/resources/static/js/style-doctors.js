document.addEventListener("DOMContentLoaded", function () {
    const doctorSelect = document.getElementById("doctor-select");
    const submitButton = document.getElementById("submit-button");
    const calendarCells = document.querySelectorAll(".calendar-cell");
    const selectedDoctorIdsInput = document.getElementById("selected-doctors-ids");

    const currentDate = new Date();
    const day = currentDate.getDay();
    const diff = currentDate.getDate() - day + (day === 0 ? -6 : 1);

    const startOfWeek = new Date(currentDate);
    startOfWeek.setDate(diff);
    const endOfWeek = new Date(startOfWeek);
    endOfWeek.setDate(startOfWeek.getDate() + 6);

    const currentWeekDatesElement = document.getElementById("current-week-dates");
    updateWeekDates(startOfWeek, endOfWeek);

    const prevWeekButton = document.getElementById("prev-week-button");
    const nextWeekButton = document.getElementById("next-week-button");

 function toggleSubmitButton() {
            if (doctorSelect && submitButton) {
                submitButton.disabled = doctorSelect.selectedOptions.length === 0;
            }
        }

    prevWeekButton.addEventListener("click", function () {
        const minPrevWeek = new Date();
        minPrevWeek.setDate(minPrevWeek.getDate() - 7);

        if (startOfWeek > minPrevWeek) {
            startOfWeek.setDate(startOfWeek.getDate() - 7);
            endOfWeek.setDate(endOfWeek.getDate() - 7);
            updateWeekDates(startOfWeek, endOfWeek);
            updateNavButtons();
            updateCalendarColors();
        }
    });

    nextWeekButton.addEventListener("click", function () {
        const maxNextWeek = new Date();
        maxNextWeek.setFullYear(maxNextWeek.getFullYear() + 1);

        if (endOfWeek <= maxNextWeek) {
            startOfWeek.setDate(startOfWeek.getDate() + 7);
            endOfWeek.setDate(endOfWeek.getDate() + 7);
            updateWeekDates(startOfWeek, endOfWeek);
            updateNavButtons();
            updateCalendarColors();
        }
    });

     doctorSelect.addEventListener("change", function () {
            toggleSubmitButton();
            if (doctorSelect.selectedOptions.length > 0) {
                const selectedDoctorIds = Array.from(doctorSelect.selectedOptions).map(option => option.value);
                selectedDoctorIdsInput.value = selectedDoctorIds.join(',');
            } else {
                selectedDoctorIdsInput.value = '';
            }
            updateCalendarColors();
        });
     // Frissítsd a gomb állapotát az oldal betöltésekor
        toggleSubmitButton();

   function updateCalendarColors() {
        if (doctorSelect) {
            const isAnyDoctorSelected = doctorSelect.selectedOptions.length > 0;
            const classToAdd = "inactive";

            calendarCells.forEach(function (cell) {
                const cellDate = new Date(cell.getAttribute("data-date"));
                if (!isAnyDoctorSelected || cellDate <= currentDate) {
                    cell.classList.add(classToAdd);
                } else {
                    cell.classList.remove(classToAdd);
                }
            });
        }
    }

    function updateWeekDates(start, end) {
        const startDateString = start.toLocaleDateString("hu-HU");
        const endDateString = end.toLocaleDateString("hu-HU");
        currentWeekDatesElement.textContent = `Aktuális hét: ${startDateString} - ${endDateString}`;
    }

    function updateNavButtons() {
        prevWeekButton.disabled = startOfWeek <= currentDate;
        nextWeekButton.disabled = endOfWeek >= new Date(currentDate.getFullYear() + 1, currentDate.getMonth(), currentDate.getDate());
    }

    // Frissítsd a gomb állapotát az oldal betöltésekor
    toggleSubmitButton();
});

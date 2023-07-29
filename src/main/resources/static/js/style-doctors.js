document.addEventListener("DOMContentLoaded", function () {
  const selectAllCheckbox = document.getElementById("selectAll");
  const checkboxes = document.getElementsByName("selectedDoctors");

  selectAllCheckbox.addEventListener("change", function () {
    const isChecked = selectAllCheckbox.checked;
    checkboxes.forEach((checkbox) => {
      checkbox.checked = isChecked;
    });
  });

  checkboxes.forEach((checkbox) => {
    checkbox.addEventListener("change", function () {
      const isAllChecked = [...checkboxes].every((c) => c.checked);
      selectAllCheckbox.checked = isAllChecked;
    });
  });
});

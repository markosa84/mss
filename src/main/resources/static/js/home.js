document.addEventListener('DOMContentLoaded', function() {
  const selectDoctorLinks = document.getElementsByClassName('select-doctor-link');

  Array.from(selectDoctorLinks).forEach(link => {
    link.style.display = 'none'; // Kezdetben elrejtjük a linkeket
  });

  // Egér belép a kártya területére
  function showSelectDoctorLink() {
   event.preventDefault(); // Megállítjuk az alapértelmezett működést (navigációt)
    this.querySelector('.select-doctor-link').style.display = 'inline';
  }

  // Egér kimegy a kártya területéről
  function hideSelectDoctorLink() {
    this.querySelector('.select-doctor-link').style.display = 'none';
  }

  // Eseménykezelők hozzárendelése a kártyákhoz
  Array.from(document.getElementsByClassName('col-lg-4')).forEach(card => {
    card.addEventListener('mouseover', showSelectDoctorLink);
    card.addEventListener('mouseout', hideSelectDoctorLink);
  });
});

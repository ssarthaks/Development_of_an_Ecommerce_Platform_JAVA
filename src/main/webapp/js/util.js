document.addEventListener("DOMContentLoaded", function () {
  const hamburger = document.querySelector(".hamburger");
  const navMenu = document.querySelector(".nav-menu");
  var body = document.body;
  hamburger.addEventListener("click", mobileMenu);

  function mobileMenu() {
    body.classList.toggle("disableScroll");
    hamburger.classList.toggle("active");
    navMenu.classList.toggle("active");
  }

  const navLinks = document.querySelectorAll(".nav-link");

  navLinks.forEach(function (navLink) {
    navLink.addEventListener("click", closeMenu);
  });

  function closeMenu() {
    body.classList.remove("disableScroll");
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
  }
  
});

    function submitOnEnter(event) {

        if (event.keyCode === 13) {

            event.preventDefault(); 
            document.querySelector('form').submit();
            return false;
        }
        return true;
    }
    
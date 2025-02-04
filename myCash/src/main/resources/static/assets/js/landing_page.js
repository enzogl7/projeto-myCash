document.getElementById('botao-comecar').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('secao-form').scrollIntoView({
        behavior: 'smooth',
        block: 'start'
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const dots = document.querySelectorAll(".dot");
    const sections = document.querySelectorAll("section");

    if (dots.length === 0 || sections.length === 0) return;

    dots.forEach(dot => {
        dot.addEventListener("click", () => {
            const targetSection = document.getElementById(dot.dataset.target);
            if (targetSection) {
                targetSection.scrollIntoView({ behavior: "smooth" });
            }
        });
    });

    const updateActiveDot = () => {
        let currentIndex = 0;
        sections.forEach((section, index) => {
            const sectionTop = section.getBoundingClientRect().top;
            if (sectionTop < window.innerHeight / 2) {
                currentIndex = index;
            }
        });

        dots.forEach(dot => dot.classList.remove("active"));
        dots[currentIndex].classList.add("active");
    };

    window.addEventListener("scroll", updateActiveDot);

    updateActiveDot();
});

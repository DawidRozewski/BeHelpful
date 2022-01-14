document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            const street = document.getElementById("street");
            const givenStreet = document.getElementById("givenStreet")
            givenStreet.innerText = street.value;

            const city = document.getElementById("city");
            const givenCity = document.getElementById("givenCity");
            givenCity.innerText = city.value

            const zipCode = document.getElementById("zipCode");
            const givenZipCode = document.getElementById("givenZipCode");
            givenZipCode.innerText = zipCode.value;

            const phoneNumber = document.getElementById("phoneNumber");
            const givenPhoneNumber = document.getElementById("givenPhoneNumber");
            givenPhoneNumber.innerText = phoneNumber.value;

            const date = document.getElementById("date");
            const givenDate = document.getElementById("givenDate")
            givenDate.innerText = date.value;

            const time = document.getElementById("time");
            const givenTime = document.getElementById("givenTime")
            givenTime.innerText = time.value;

            const comment = document.getElementById("comment");
            const givenComment = document.getElementById("givenComment")
            givenComment.innerText = comment.value;

            const institution = document.getElementsByName("institution");
            let institutionValue;
            for (let i = 0; i < institution.length; i++) {
                if (institution[i].checked) {
                    if (institution[i].value == 1) {
                        institutionValue = "Fundacja 'Dla dzieci'";
                    }
                    if (institution[i].value == 2) {
                        institutionValue = "Fundacja 'Bez domu'";
                    }
                    if (institution[i].value == 3) {
                        institutionValue = "Fundacja 'Dbam o Zdrowie'";
                    }
                    if (institution[i].value == 4) {
                        institutionValue = "Fundacja 'A kogo'";
                    }
                }
            }
            const givenInstitution = document.getElementById("givenInstitution");
            givenInstitution.innerText = institutionValue;


            const categories = document.getElementsByName("categories");
            let categoriesName = " ";
            for (let i = 0; i < categories.length; i++) {
                if (categories[i].checked) {
                    if (categories[i].value == 1) {
                        categoriesName += "ubrania, które nadają się do ponownego użycia | ";
                    }
                    if (categories[i].value == 2) {
                        categoriesName += "ubrania, do wyrzucenia | ";
                    }
                    if (categories[i].value == 3) {
                        categoriesName += "zabawki | ";
                    }
                    if (categories[i].value == 4) {
                        categoriesName += "książki | ";
                    }
                    if (categories[i].value == 5) {
                        categoriesName += "inne | ";
                    }
                }
            }
            const bagsQuantity = document.getElementById("bags");
            const givenBags = document.getElementById("givenBags");
             givenBags.innerText = bagsQuantity.value + " worki ";

            let categoriesNameNoLastCharacter = categoriesName.slice(0, -2);
            const givenCategories = document.getElementById("givenCategories");
            givenCategories.innerText = "Kategorie: " + categoriesNameNoLastCharacter;


        }
    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});

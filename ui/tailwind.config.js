/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    container: {
      center: true,
      padding: "15px",
      screens: {
        "max-513": { max: "513px" },
        sm: "640px",
        md: "768px",
        lg: "992px",
        xl: "1200px",
      },
    },
    fontFamily: {
      primary: "var(--font-robotoSlab)",
    },
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        primary: "#ff7b29",
        secondary: {
          DEFAULT: "#F4A41C",
          hover: "#00e187",
        },
        thirdly: {
          DEFAULT: "#fff3ed",
        },
      },
      keyframes: {
        motion: {
          "0%, 100%": { transform: "translateY(0px)" },
          "50%": { transform: "translateY(3px)" },
        },
        roadAnimation: {
          "0%": { transform: "translateX(0px)" },
          "100%": { transform: "translateX(-350px)" },
        },
        "caret-blink": {
          "0%,70%,100%": { opacity: "1" },
          "20%,50%": { opacity: "0" },
        },
      },
      animation: {
        motion: "motion 1s linear infinite",
        roadAnimation: "roadAnimation 1.4s linear infinite",
        "caret-blink": "caret-blink 1.25s ease-out infinite",
      },
    },
  },
  // plugins: [require("tailwindcss-animate")],
};

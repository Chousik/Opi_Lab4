`<template>
  <div class="picture">
    <svg @click="handleClick" width="500" height="500">
      <rect class="figure"
            :width="currentR * 25"
            :height="currentR * 50"
            :x="250 - currentR * 25"
            :y="250 - currentR * 50"
            fill="rgba(209, 217, 230, 0.80)"
      />
      <path class="figure"
            :d="`M250 250 L 250 ${250-currentR*50} A ${currentR*50} ${currentR*50} 0 0 1 ${250 + currentR*50} 250 Z`"
            fill="rgba(209, 217, 230, 0.80)"
      />
      <polygon class="figure"
               :points="`250 250 ${250+currentR*50} 250 250 ${250+currentR*50}`"
               fill="rgba(209, 217, 230, 0.80)"/>
      <staticPicture></staticPicture>
      <circle class="answerDot" cx="100" cy="100" r="0" fill="black"/>
    </svg>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>
import staticPicture from '../subMainPage/staticpictureelements.vue';


export default {
  name: 'GraphComponent',
  components: {
    staticPicture
  },
  data() {
    return {
      errorMessage: "",
    };
  },
  props: {
    rValue: {
      type: String,
      required: true
    },
    points: {
      type: Array,
      required: true
    }
  },
  computed: {
    currentR() {
      return parseFloat(this.rValue) || 0;
    }
  },
  methods: {
    handleClick(event) {
      const clickX = (event.offsetX - 250) / 50;
      const clickY = (250 - event.offsetY) / 50;
      console.log(`Клик по картинке: X=${clickX}, Y=${clickY}`);
      if (isNaN(clickX) || clickX < -5 || clickX > 3) {
        this.errorMessage = 'X должен быть числом от -5 до 3.';
        return;
      }
      if (isNaN(clickY) || clickY < -3 || clickY > 3) {
        this.errorMessage = 'Y должен быть числом от -3 до 3.';
        return false;
      }
      this.errorMessage = '';
      this.$emit('submit-data', {
        x: clickX,
        y: clickY,
        r: this.currentR
      });
    },
  }
};
</script>

<style scoped>
.picture {
  grid-area: picture;
  justify-self: center;
  margin: auto;
}

.figure {
  -webkit-filter: drop-shadow(3px 3px 2px rgba(53, 53, 87, 0.7));
  filter: drop-shadow(0px 0px 2px rgb(53, 53, 87, 0.7));
}
.error {
  color: red;
  margin-top: 10px;
  font-size: 14px;
}
</style>

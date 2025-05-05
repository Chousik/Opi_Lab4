<template>
  <div class="wrapp">
    <FormComponent
        @submit-data="handleSubmit"
        @update-graph="updateGraph"
    />

    <GraphComponent :r-value="currentR" :points="points" @submit-data="handleSubmit"/>
  </div>
</template>

<script>
import FormComponent from '../components/subMainPage/inputData.vue';
import GraphComponent from '../components/subMainPage/picture.vue';
import axios from "axios";

export default {
  name: 'PageComponent',
  components: {
    FormComponent,
    GraphComponent
  },
  data() {
    return {
      currentR: '',
      points: []
    };
  },
  methods: {
    async handleSubmit(data) {
      const response = await axios.post('http://localhost:8080/api/add', data);

      console.log('Получены данные:', response);
    },

    updateGraph(newR) {
      this.currentR = newR;
    },
  },
};
</script>

<style scoped>

.wrapp {
  display: grid;
  grid-template-areas:
    "hat hat"
    "main picture"
    "results results";
  gap: 20px;
  height: 100vh;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 200px 1fr 1fr;
  font-size: 18px;
}

</style>
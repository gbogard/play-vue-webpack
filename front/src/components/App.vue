<template>
    <div id="hello">
        <div id="images">
            <img src="/assets/images/scala.png" alt="Scala"> +
            <img src="/assets/images/play.png" alt="Play Framework"> +
            <img src="/assets/images/vue.png" alt="Vue.js">
        </div>
        <h1>Hello from <span class="text-primary">Vue.js</span>!</h1>
    </div>
</template>

<script>
    export default {

    }
</script>

<style scoped lang="scss">
    #hello {
        width: 600px;
        margin: 0 auto;
        color: gray;
        text-align: center;
        position: relative;
        top: 50%;
        transform: translateY(-50%);
    }

    #images {
        font-size: 2.3em;
        font-weight: bold;
        margin-bottom: 1em;
        img {
            height: 100px;
        }
    }
</style>
package com.dicoding.jpsubsatu.utils

import com.dicoding.jpsubsatu.data.MovieEntity
import com.dicoding.jpsubsatu.data.TvshowEntity

object DataDummy {
    fun generateMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(MovieEntity(
                "M01",
                "Skylines (2020)",
                "Science Fiction, Action",
                "When a virus threatens to turn the now earth-dwelling friendly alien hybrids against humans, Captain Rose Corley must lead a team of elite mercenaries on a mission to the alien world in order to save what's left of humanity.",
                "https://www.themoviedb.org/t/p/original/2W4ZvACURDyhiNnSIaFPHfNbny3.jpg"
        ))

        movies.add(MovieEntity(
                "M02",
                "Wonder Woman 1984 (2020)",
                "Fantasy, Action, Adventure",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "https://www.themoviedb.org/t/p/original/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"
        ))

        movies.add(MovieEntity(
                "M03",
                "Batman: Soul of the Dragon (2021)",
                "Animation, Action, Adventure, Crime, Fantasy",
                "Bruce Wayne faces a deadly menace from his past, with the help of three former classmates: world-renowned martial artists Richard Dragon, Ben Turner and Lady Shiva.",
                "https://www.themoviedb.org/t/p/original/jzhbZZWHMOxVF9uz8lNilDEx8dl.jpg"
        ))

        movies.add(MovieEntity(
                "M04",
                "Jumanji: The Next Level (2019)",
                "Adventure, Comedy, Fantasy",
                "As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world’s most dangerous game.",
                "https://www.themoviedb.org/t/p/original/jyw8VKYEiM1UDzPB7NsisUgBeJ8.jpg"
        ))

        movies.add(MovieEntity(
                "M05",
                "Mulan (2020)",
                "Adventure, Fantasy",
                "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
                "https://www.themoviedb.org/t/p/original/aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg"
        ))

        movies.add(MovieEntity(
                "M06",
                "Monsters of Man (2020)",
                "Science Fiction",
                "A robotics company vying to win a lucrative military contract team up with a corrupt CIA agent to conduct an illegal live field test. They deploy four weaponized prototype robots into a suspected drug manufacturing camp in the Golden Triangle, assuming they'd be killing drug runners that no one would miss. Six doctors on a humanitarian mission witness the brutal slaughter and become prime targets.",
                "https://www.themoviedb.org/t/p/original/1f3qspv64L5FXrRy0MF8X92ieuw.jpg"
        ))

        movies.add(MovieEntity(
                "M07",
                "Peninsula (2020)",
                "Action, Horror, Thriller",
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                "https://www.themoviedb.org/t/p/original/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg"
        ))

        movies.add(MovieEntity(
                "M08",
                "Avengers: Infinity War (2018)",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "https://www.themoviedb.org/t/p/original/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
        ))

        movies.add(MovieEntity(
                "M09",
                "Curious George: Go West, Go Wild (2020)",
                "Animation, Family",
                "While farm-sitting, George accidentally lets the farm animals escape. Ted, George and wannabe cowboy Emmett must round 'em up, accounting for every animal -- and emerging with some new skills, to boot.",
                "https://www.themoviedb.org/t/p/original/7WWOiP8F6PHjXpJrnRZfvO2XNW.jpg"
        ))

        movies.add(MovieEntity(
                "M10",
                "Spider-Man: Far from Home (2019)",
                "Action, Adventure, Science Fiction",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "https://www.themoviedb.org/t/p/original/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg"
        ))

        return movies
    }

    fun generateTvshows() : ArrayList<TvshowEntity> {
        val tvshows = ArrayList<TvshowEntity>()
        tvshows.add(TvshowEntity(
                "T01",
                "The Promised Neverland (2019)",
                "Animation, Mystery, Sci-Fi & Fantasy, Action & Adventure, Drama",
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.",
                "https://www.themoviedb.org/t/p/original/yxdeII5tI8qqiERcMxjW9DfB6Gz.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T02",
                "Banana Fish (2018)",
                "Action & Adventure, Drama, Mystery, Animation",
                "Nature made Ash Lynx beautiful; nurture made him a cold ruthless killer. A runaway brought up as the adopted heir and sex toy of \"Papa\" Dino Golzine, Ash, now at the rebellious age of seventeen, forsakes the kingdom held out by the devil who raised him. But the hideous secret that drove Ash's older brother mad in Vietnam has suddenly fallen into Papa's insatiably ambitious hands—and it's exactly the wrong time for Eiji Okamura, a pure-hearted young photographer from Japan, to make Ash Lynx's acquaintance",
                "https://www.themoviedb.org/t/p/original/1UV5di9UIXwrpCW3xQ4RNli5hEV.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T03",
                "The Wilds (2020)",
                "Mystery, Drama",
                "A group of teen girls from different backgrounds must fight for survival after a plane crash strands them on a deserted island. The castaways both clash and bond as they learn more about each other, the secrets they keep, and the traumas they've all endured. But there’s just one twist… these girls did not end up on this island by accident.",
                "https://www.themoviedb.org/t/p/original/gHBtyMdHbWoM3tpM8VZymer8HfF.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T04",
                "Your Lie in April (2014)",
                "Animation, Comedy, Drama\n",
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                "https://www.themoviedb.org/t/p/original/nksFLYTydth9OYVpMuMbtOBkvMO.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T05",
                "Weightlifting Fairy Kim Bok-Joo (2016)",
                "Comedy, Drama",
                "A spunky female weightlifter and free-spirited male swimmer meet on campus, only to find out their pasts may be intertwined.",
                "https://www.themoviedb.org/t/p/original/4CrTjZ5MYdF5dtSockIStCkiX5C.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T06",
                "Hotel Del Luna (2019)",
                "Sci-Fi & Fantasy, Drama, Comedy\n",
                "The Hotel Del Luna, located in Seoul, is not like any other hotel: its client are all ghosts. Jang Man-Wol, stuck in the hotel for the past millennium, meets Koo Chan-Sung, the new manager.",
                "https://www.themoviedb.org/t/p/original/8bizZsXoAsOTbhyFKfBogC8mgG2.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T07",
                "The Umbrella Academy (2019)",
                "Action & Adventure, Sci-Fi & Fantasy, Drama",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "https://www.themoviedb.org/t/p/original/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T08",
                "My Little Monster (2012)",
                "Animation, Comedy, Drama\n",
                "Mizutani Shizuku finds peace in her studies.  Working hard to stay at the top of the class, alone but focused.  When she is tasked with delivering class materials to Yoshida Haru, her world starts to rapidly expand.  With some new friends and feelings, will she be able to stay focused on her studies?",
                "https://www.themoviedb.org/t/p/original/qmGAYDNuENa94kGV56Oe0Kwm3dX.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T09",
                "Tower of God (2020)",
                "Animation, Drama, Mystery, Action & Adventure, Sci-Fi & Fantasy",
                "There is a tower that summons chosen people called \"Regulars\" with the promise of granting their deepest desires. Whether it be wealth, fame, authority, or something that surpasses them all—everything awaits those who reach the top.",
                "https://www.themoviedb.org/t/p/original/fUs9KMgEJj3wYJGa75MtPSdyJXk.jpg"
        ))

        tvshows.add(TvshowEntity(
                "T10",
                "Domestic Girlfriend (2019)",
                "Drama, Animation",
                "Natsuo is a high school boy who is experiencing the crushing despair of unrequited love. To make matters worse, the person he is in love with is his teacher, Hina. In an attempt to lift his spirits, he attends a mixer where he meets a girl named Rui. The two sleep together, expecting never to see one another again, but fate has other plans. His life suddenly becomes more complicated when his father comes home and announces he has remarried a woman with two daughters whom Natsuo has met before: Hina and Rui!",
                "https://www.themoviedb.org/t/p/original/d6yGpIrk2RghwIEuyDr1KMYEDyE.jpg"
        ))

        return tvshows
    }
}